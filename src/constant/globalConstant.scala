package constant

/** 此对象用于记录项目中使用到的常量名, 主要分为三部分
  * 第一部分是所有自定义函数的名字
  * 第二部分是所有表格的名字
  *
  * final val + 首字母大写, 是scala源码定义常量的语法规范
  * 因为scala中, 首字母小写在某些环境下会被视为变量
  * 但是如果先java那样, 全字母大写, 又太难认单词了
  */
object globalConstant {

  /****** ========== 自定义函数名 ========== ******/
  final val UdfName_get_target_ids = "get_target_ids"
  final val UdfName_interactive_click_list = "interactive_click_list"
  final val UdfName_null_colume = "null_colume"


  /****** ========== 表格名 ========== ******/
  final val TableName_ods_01_user_action_log = "ods_01_user_action_log"
  final val TableName_dw_actlog_launch = "dw_actlog_launch"
  final val TableName_dw_actlog_exit = "dw_actlog_exit"
  final val TableName_dw_actlog_pageview = "dw_actlog_pageview"
  final val TableName_dw_actlog_product_view = "dw_actlog_product_view"
  final val TableName_temp_interactive_click = "temp_interactive_click"
  final val TableName_dw_actlog_shoper_attention = "dw_actlog_shoper_attention"
  final val TableName_dw_actlog_order = "dw_actlog_order"
  final val TableName_dw_actlog_order_pay = "dw_actlog_order_pay"



}