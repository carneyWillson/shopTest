package etl.transformation

import org.apache.spark.{SparkConf, sql}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
import constant.nameInfo._
import org.apache.spark.sql.types.StringType
import util.Implicit._
import util.myudf
import tableSchema.{dw_shop, ods_shop}
import tableSchema.valueEnum._

object Init_dw {
  def apply(sparkSession: SparkSession, path: String): Init_dw = new Init_dw(sparkSession, path)
}

class Init_dw(sparkSession: SparkSession, path: String) {

  import sparkSession.implicits._

  // 加载数据, 并注册表
  val Ods_01_user_action_log = sparkSession.read
  .load(path)
  .toDF.cache
  Ods_01_user_action_log.createOrReplaceTempView(TableName_ods_01_user_action_log)

  // 初始化中间表
  lazy val temp_interactive_click = init_temp_interactive_click
  // 用于充数的列
  val null_colume = udf(myudf.null_colume _)

  def showAll: Unit = {

    init_dw_actlog_launch.show()
    init_dw_actlog_exit.show()

    init_dw_actlog_pageview.show()
    init_dw_actlog_product_view.show()
    // 店铺关注主题
    init_dw_actlog_shoper_attention.show()
    // 订单(生成)主题
    init_dw_actlog_order.show()
    // 支付主题
    init_dw_actlog_order_pay.show()
  }


  // 核心方法, 初始化dwb层
  def initDwb: Unit = {
    // 启动主题
    init_dw_actlog_launch
    // 退出主题
    init_dw_actlog_exit
    // 页面曝光主题
    init_dw_actlog_pageview
    // dw: 产品浏览, 滑动主题(多了event_type和goods_id)
    init_dw_actlog_product_view

    // 用户搜索主题
    // 产品评论主题

    // 店铺关注主题
    init_dw_actlog_shoper_attention
    // 订单(生成)主题
    init_dw_actlog_order
    // 支付主题
    init_dw_actlog_order_pay
  }


  def init_dw_actlog_launch: sql.DataFrame = {
    // 导入父表的列名
    import tableSchema.ods_shop.ods_01_user_action_log._

    val dw_actlog_launch = Ods_01_user_action_log
      // 居然要三个等号..
      .where($"$Action" === s"${action.Launch}")
      .selectExpr(dw_shop.dw_actlog_launch:_*)

    dw_actlog_launch.createOrReplaceTempView(TableName_dw_actlog_launch)
    dw_actlog_launch
  }

 def init_dw_actlog_exit: sql.DataFrame = {
   // 导入父表的列名
   import tableSchema.ods_shop.ods_01_user_action_log._

    val dw_actlog_exit = Ods_01_user_action_log
      .where($"$Action" === s"${action.Exit}")
      .selectExpr(dw_shop.dw_actlog_exit:_*)

    dw_actlog_exit.createOrReplaceTempView(TableName_dw_actlog_exit)
    dw_actlog_exit
  }

  def init_dw_actlog_pageview: sql.DataFrame ={
    // 导入父表的列名
    import tableSchema.ods_shop.ods_01_user_action_log._

    val dw_actlog_pageview = Ods_01_user_action_log
      .where(s"$Action in ('${action.Page_enter_h5}', '${action.Page_enter_native}')")
      .selectExpr(dw_shop.dw_actlog_pageview:_*)

    dw_actlog_pageview.createOrReplaceTempView(TableName_dw_actlog_pageview)
    dw_actlog_pageview
  }

 def init_dw_actlog_product_view: sql.DataFrame ={
   // 导入父表的列名
   import tableSchema.ods_shop.ods_01_user_action_log._

   // 将方法转为udf
   val get_target_ids = udf(myudf.get_target_ids _)
   val dw_actlog_product_view = Ods_01_user_action_log
     .where(s"$Event_type in ('${event_type.View}', '${event_type.Slide}')")
     .withColumn(s"${dw_shop.dw_actlog_product_view.Good_id}", get_target_ids($"Extinfo"))
     .selectExpr(dw_shop.dw_actlog_product_view:_*)

//    var sql =
//      """
//        |select action, session_id, device_id, user_id
//        |, os, os_version, manufacturer, carrier, network_type
//        |, area_code, longitude, latitude, page_id, event_type
//        |, get_good_id(extinfo) as goods_id
//        |, ct
//        |from allinfo
//        |where event_type in ('view', 'slide')
//      """.stripMargin

   dw_actlog_product_view.createOrReplaceTempView(TableName_dw_actlog_product_view)
   dw_actlog_product_view
 }

  // 之后的表都需要解析json, 然后相应的增加列
  // 此处建立了一个临时表, 完成了对json的解析, 方便后续操作
  def init_temp_interactive_click = {
    // 导入父表的列名
    import tableSchema.ods_shop.ods_01_user_action_log._

    val temp0_interactive_click = Ods_01_user_action_log
      .where($"$Action" === s"${action.Interactive}")
      .where($"$Event_type" === s"${event_type.Click}")
      .rdd
      .map { row =>
        val list = myudf.interactive_click_list(row.getAs[String](s"$Extinfo"))
        Row.fromSeq(row.toSeq.toList:::list)
      }

    // 导入新列的列名
    import dw_shop.temp_interactive_click._
    // 将修改后的schema信息和rdd联系起来
    val temp_interactive_click = sparkSession
      .createDataFrame(temp0_interactive_click
        , Ods_01_user_action_log.schema
          .add(s"$Target_action", StringType)
          .add(s"$Target_id", StringType)
          .add(s"$Target_pay_type", StringType)
          .add(s"$Target_pay_code", StringType))
    temp_interactive_click.createOrReplaceTempView(TableName_temp_interactive_click)
    temp_interactive_click.cache()
  }

  def init_dw_actlog_shoper_attention: sql.DataFrame ={
    // 导入父表的列名
    import dw_shop.temp_interactive_click._

    val dw_actlog_shoper_attention = temp_interactive_click
      .where($"$Target_action" === s"${target_action.Attention_shoper}")
      .selectExpr(dw_shop.dw_actlog_shoper_attention:_*)

    dw_actlog_shoper_attention.createOrReplaceTempView(TableName_dw_actlog_shoper_attention)
    dw_actlog_shoper_attention
  }

  def init_dw_actlog_order: sql.DataFrame ={
    // 导入父表的列名
    import ods_shop.innerTable.extinfo_interactive_click._

    val dw_actlog_order = temp_interactive_click
      .where($"$Target_action" === s"${target_action.Order_commit}")
      .withColumn(s"${dw_shop.dw_actlog_order.Target_price}", null_colume())
      .selectExpr(dw_shop.dw_actlog_order:_*)

    dw_actlog_order.createOrReplaceTempView(TableName_dw_actlog_order)
    dw_actlog_order
  }

  def init_dw_actlog_order_pay: sql.DataFrame ={
    import ods_shop.innerTable.extinfo_interactive_click._

    val dw_actlog_order_pay = temp_interactive_click
      .where($"$Target_action" === s"${target_action.Order_pay}")
      .withColumn(s"${dw_shop.dw_actlog_order_pay.Target_price}", null_colume())
      .withColumn(s"${dw_shop.dw_actlog_order_pay.Target_status}", null_colume())
      .selectExpr(dw_shop.dw_actlog_order_pay:_*)

    dw_actlog_order_pay.createOrReplaceTempView(TableName_dw_actlog_order_pay)
    dw_actlog_order_pay
  }
}
