package util


object myudf {

  def get_target_ids(json: String): Array[String] = {
    val matcher = "\"target_ids\":\\[(.*?)\\]".r
      .pattern.matcher(json)
    if (matcher.find()) {
      matcher.group(1).split(",")
    } else {
      Array[String]()
    }
  }

  def interactive_click_list(json: String): List[String] = {
    import scala.util.parsing.json.JSON
    def getInfo(json1: String) = JSON.parseFull(json1) match {
      // 编译的时候, 不会保留map键值对的具体类型
      // 这里map的值可能是String, 也可能是Map
      case Some(map: Map[String, Any]) => map
      case None => Map[String, Any]()
    }

    // 整体解析json
    import tableSchema.ods_shop.innerTable.extinfo_interactive_click._
    val allInfo = getInfo(json)
    val target_action = allInfo.getOrElse(s"$Target_action", "").toString
    val target_ids = allInfo.getOrElse(s"$Target_ids", "").toString


    // 看一下有没有target_pay属性, 如果有就进一步解析
    val target_pay = allInfo.get(s"$Target_pay")
    var pay_type = ""
    var pay_code = ""
    if (target_pay.isDefined){
      // 解析target_pay
      val target_pays = target_pay.get.asInstanceOf[Map[String, String]]
      pay_type = target_pays.getOrElse(s"$Target_pay_type", "")
      pay_code = target_pays.getOrElse(s"$Target_pay_code", "")
    }

    List(target_action, target_ids, pay_type, pay_code)
  }

  def null_colume() = {
    "null"
  }
}
