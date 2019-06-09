package tableSchema.dw_shop

import tableSchema.ods_shop.ods_01_user_action_log

object dw_actlog_launch extends Enumeration {

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
  val Ct = Value(s"${ods_01_user_action_log.Ct}")

}
