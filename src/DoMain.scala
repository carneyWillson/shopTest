import etl.transformation.Init_dw
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

// 一切的入口
object DoMain {
  def main(args: Array[String]): Unit = {
    // 建立连接
    val conf = new SparkConf().setMaster("local[*]").setAppName("odsRoDwb")
    def sparkSession = SparkSession.builder().config(conf).getOrCreate()
    val path = ("C:\\myDemo\\shop\\bdp_day=20190630\\156190620000045h5fcd3")
    Init_dw(sparkSession, path).showAll
  }
}
