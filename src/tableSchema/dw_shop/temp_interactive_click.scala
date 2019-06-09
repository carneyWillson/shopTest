package tableSchema.dw_shop

import tableSchema.ods_shop.innerTable.extinfo_interactive_click
import tableSchema.ods_shop.ods_01_user_action_log

object temp_interactive_click extends Enumeration {

  val Action = Value(s"${ods_01_user_action_log.Action}")
  val Session_id = Value(s"${ods_01_user_action_log.Session_id}")
  val Device_id = Value(s"${ods_01_user_action_log.Device_id}")
  val User_id = Value(s"${ods_01_user_action_log.User_id}")
  val Os = Value(s"${ods_01_user_action_log.Os}")
  val Os_version = Value(s"${ods_01_user_action_log.Os_version}")
  val Manufacturer = Value(s"${ods_01_user_action_log.Manufacturer}")
  val Carrier = Value(s"${ods_01_user_action_log.Carrier}")
  val Network_type = Value(s"${ods_01_user_action_log.Network_type}")
  val Area_code = Value(s"${ods_01_user_action_log.Area_code}")
  val Longitude = Value(s"${ods_01_user_action_log.Longitude}")
  val Latitude = Value(s"${ods_01_user_action_log.Latitude}")
  val Page_id = Value(s"${ods_01_user_action_log.Page_id}")
  val Extinfo = Value(s"${ods_01_user_action_log.Extinfo}")
  val Ct = Value(s"${ods_01_user_action_log.Ct}")
  // 新增列
  val Target_action = Value(s"${extinfo_interactive_click.Target_action}")
  val Target_id = Value(constant.newColumn.Target_id_from_Target_ids)
  val Target_pay_code = Value(s"${extinfo_interactive_click.Target_pay_code}")
  val Target_pay_type = Value(constant.newColumn.Target_type_from_Pay_type)

}