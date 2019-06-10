package constant

/**
  * 项目中使用了枚举类来对应项目中的每一张表
  * 因此, 对表中列名的调用建议使用表格所对应的枚举对象来完成
  * 表格的枚举对象中的列名 会引用下述常量
  */
object columnName {
  // com的calumn是每个表格特有的, 各表格间同名不同义
  final val Col_com_ct = "ct"

  final val Col_ods_action = "action"
  final val Col_ods_event_type = "event_type"
  final val Col_ods_session_id = "session_id"
  final val Col_ods_device_id = "device_id"
  final val Col_ods_user_id = "user_id"
  final val Col_ods_os = "os"
  final val Col_ods_os_version = "os_version"
  final val Col_ods_manufacturer = "manufacturer"
  final val Col_ods_carrier = "carrier"
  final val Col_ods_network_type = "network_type"
  final val Col_ods_area_code = "area_code"
  final val Col_ods_longitude = "longitude"
  final val Col_ods_latitude = "latitude"
  final val Col_ods_page_id = "page_id"
  final val Col_ods_extinfo = "extinfo"

  final val Col_inner_target_action = "target_action"
  final val Col_inner_target_ids = "target_ids"
  final val Col_inner_target_pay = "target_pay"
  final val Col_inner_target_pay_type = "pay_type"
  final val Col_inner_target_pay_code = "pay_code"
  final val Col_inner_target_content = "target_content"
  final val Col_inner_target_order = "target_order"
  final val Col_inner_user_name = "user_name"
  final val Col_inner_user_pass = "user_pass"
  final val Col_inner_validate_type = "validate_type"
  final val Col_inner_event_result = "event_result"
  final val Col_inner_target_keys = "target_keys"

  /****** ========== 改了名字的列名 ========== ******/
  final val Col_dw_good_id_from_target_ids = "good_id"
  final val Col_dw_target_id_from_target_ids = "target_id"
  final val Col_dw_target_type_from_pay_type = "target_type"
}
