package tableSchema.dw_shop

import constant.columnName._

object temp_interactive_click extends Enumeration {

  val Col_action = Value(Col_ods_action)
  val Col_session_id = Value(Col_ods_session_id)
  val Col_device_id = Value(Col_ods_device_id)
  val Col_user_id = Value(Col_ods_user_id)
  val Col_os = Value(Col_ods_os)
  val Col_os_version = Value(Col_ods_os_version)
  val Col_manufacturer = Value(Col_ods_manufacturer)
  val Col_carrier = Value(Col_ods_carrier)
  val Col_network_type = Value(Col_ods_network_type)
  val Col_area_code = Value(Col_ods_area_code)
  val Col_longitude = Value(Col_ods_longitude)
  val Col_latitude = Value(Col_ods_latitude)
  val Col_page_id = Value(Col_ods_page_id)
  val Col_ct = Value(Col_com_ct)
  // 新增列
  val Col_target_action = Value(Col_inner_target_action)
  val Col_target_id = Value(Col_dw_target_id_from_target_ids)
  val Col_target_pay_code = Value(Col_inner_target_pay_code)
  val Col_target_pay_type = Value(Col_inner_target_pay_type)

}