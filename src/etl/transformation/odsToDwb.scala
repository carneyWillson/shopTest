package etl.transformation

import constant.allColume.actionEnum
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import constant.allColume.columeName._
import constant.{tableName, udfName}
import util.udf

object odsToDwb {

  val conf = new SparkConf().setMaster("local[*]").setAppName("odsRoDwb")
  val sparkSession = SparkSession.builder().config(conf).getOrCreate()
  import sparkSession.implicits._

  val sourceTable = sparkSession.read
  .load("C:\\myDemo\\shop\\bdp_day=20190630\\156190620000045h5fcd3")
  .toDF.cache
  sourceTable.createOrReplaceTempView(tableName.Ods_only_table)

  def main(args: Array[String]): Unit = {
    init_dw_actlog_launch
  }

  // 核心方法, 初始化dwb层
  def initDwb = {
    // 启动主题
    init_dw_actlog_launch
    // 退出主题
    init_dw_actlog_exit
    // 页面曝光主题
    init_dw_actlog_pageview
    // dw: 产品浏览, 滑动主题(多了event_type和goods_id)
    scala.List
  }


  def init_dw_actlog_launch: Unit = {

    val dw_actlog_launch = sourceTable
      // 居然要三个等号..
      .where($"$Action" === $"${actionEnum.Launch}")
      .select(Action, Session_id, Device_id, User_id
        , Os, Os_version, Manufacturer, Carrier
        , Network_type, Area_code, Longitude, Latitude
        , Ct)
  }

  def init_dw_actlog_exit: Unit = {
    val dw_actlog_exit = sourceTable
      .where($"$Action" === $"${actionEnum.Exit}")
      .select(Action, Session_id, Device_id, User_id
        , Os, Os_version, Manufacturer, Carrier
        , Network_type, Area_code, Longitude, Latitude
        , Ct)
  }

  def init_dw_actlog_pageview: Unit ={
    val dw_actlog_pageview = sourceTable
      .where($"$Action" === $"${actionEnum.Page_enter_h5}")
      .select(Action, Session_id, Device_id, User_id
        , Os, Os_version, Manufacturer, Carrier
        , Network_type, Area_code, Longitude, Latitude
        , Page_id, Ct)
  }

  def init_dw_actlog_product_view: Unit ={
    // 注册下
    sparkSession.udf.register(udfName.Get_target_ids, udf.get_target_ids _)
    var s =
      s"""
        |select $Action, $Session_id, $Device_id, $User_id
        |, $Os, $Os_version, $Manufacturer, $Carrier
        |, $Network_type, $Area_code, $Longitude, $Latitude
        |, $Page_id, $Event_type
        |, ${udfName.Get_target_ids}($Extinfo) as 
      """.stripMargin
    var sql =
      """
        |select action, session_id, device_id, user_id
        |, os, os_version, manufacturer, carrier, network_type
        |, area_code, longitude, latitude, page_id, event_type
        |, get_good_id(extinfo) as goods_id
        |, ct
        |from allinfo
        |where event_type in ('view', 'slide')
      """.stripMargin
    val dw_actlog_product_view =
  }




//  val dw_actlog_launch =

}
