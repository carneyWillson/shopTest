import etl.transformation.Init_dw
import org.apache.spark.sql.SparkSession

// 一切的入口
object DoMain {
  def main(args: Array[String]): Unit = {
    // 建立连接
    def sparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("odsRoDwb")
      .config("spark.sql.warehouse.dir", "C:\\myDemo\\hivemete")
      .enableHiveSupport()
      .getOrCreate()
    val path = ("file:///C:\\myDemo\\shop\\bdp_day=20190630\\156190620000045h5fcd3")
    Init_dw(sparkSession, path).init_dw_actlog_launch
    sparkSession.sql("show databases").show()


  }
}
