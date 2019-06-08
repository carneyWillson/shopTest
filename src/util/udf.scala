package util

import org.apache.spark.sql.SparkSession
import constant.udfName._

object udf {


  def initUdf(sparkSeesion: SparkSession): Unit ={
    sparkSeesion.udf.register(Get_target_ids, get_target_ids _)
  }

  def get_target_ids(json: String) = {
    val matcher = "\"target_ids\":\\[(.*?)\\]".r
      .pattern.matcher(json)
    if (matcher.find()) {
      matcher.group(1).split(",")
    } else {
      Array[String]()
    }
  }
}
