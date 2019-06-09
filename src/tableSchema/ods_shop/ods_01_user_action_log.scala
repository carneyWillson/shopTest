package tableSchema.ods_shop
import constant.nameInfo._

object ods_01_user_action_log extends Enumeration {

  val Action = Value("action")
  val Event_type = Value("event_type")
  val Session_id = Value("session_id")
  val Device_id = Value("device_id")
  val User_id = Value("user_id")
  val Os = Value("os")
  val Os_version = Value("os_version")
  val Manufacturer = Value("manufacturer")
  val Carrier = Value("carrier")
  val Network_type = Value("network_type")
  val Area_code = Value("area_code")
  val Longitude = Value("longitude")
  val Latitude = Value("latitude")
  val Page_id = Value("page_id")
  val Extinfo = Value("extinfo")
  val Ct = Value("ct")

}
