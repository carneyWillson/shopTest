package constant.allColume

// 记录涉及到的列名
object columeName {

  // ods层源表的列
  // final val + 首字母大写, 是scala源码定义常量的语法规范
  // 因为scala中, 首字母小写在某些环境下会被视为变量
  // 但是如果先java那样, 全字母大写, 又太难认单词了
  final val Action = "action"
  final val Event_type = "event_type"
  final val Session_id = "session_id"
  final val Device_id = "device_id"
  final val User_id = "user_id"
  final val Os = "os"
  final val Os_version = "os_version"
  final val Manufacturer = "manufacturer"
  final val Carrier = "carrier"
  final val Network_type = "network_type"
  final val Area_code = "area_code"
  final val Longitude = "longitude"
  final val Latitude = "latitude"
  final val Page_id = "page_id"
  final val Extinfo = "extinfo"
  final val Ct = "ct"

}