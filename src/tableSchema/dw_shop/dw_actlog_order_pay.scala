package tableSchema.dw_shop

import tableSchema.ods_shop.innerTable.extinfo_interactive_click
import tableSchema.ods_shop.ods_01_user_action_log

object dw_actlog_order_pay extends Enumeration {

  val Action = Value(s"${temp_interactive_click.Action}")
  val Session_id = Value(s"${temp_interactive_click.Session_id}")
  val Device_id = Value(s"${temp_interactive_click.Device_id}")
  val User_id = Value(s"${temp_interactive_click.User_id}")
  val Os = Value(s"${temp_interactive_click.Os}")
  val Os_version = Value(s"${temp_interactive_click.Os_version}")
  val Manufacturer = Value(s"${temp_interactive_click.Manufacturer}")
  val Carrier = Value(s"${temp_interactive_click.Carrier}")
  val Network_type = Value(s"${temp_interactive_click.Network_type}")
  val Area_code = Value(s"${temp_interactive_click.Area_code}")
  val Longitude = Value(s"${temp_interactive_click.Longitude}")
  val Latitude = Value(s"${temp_interactive_click.Latitude}")
  val Page_id = Value(s"${temp_interactive_click.Page_id}")
  val Target_action = Value(s"${temp_interactive_click.Target_action}")
  val Target_id = Value(s"${temp_interactive_click.Target_id}")
  val Target_price = Value("target_price")
  val Target_status = Value("target_status")
  val Target_type = Value(s"${temp_interactive_click.Target_pay_type}")
  val Ct = Value(s"${temp_interactive_click.Ct}")






}