package test

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object chickAllTable {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sparkSession = SparkSession.builder().config(conf).getOrCreate()
//    val category_dim = sparkSession.read.load("file:///C:\\Users\\WangJiaLi\\Desktop\\shop\\data\\dim_shop.db\\category_dim\\part-00000-58b5471e-34f8-48f2-966c-e12f8c42f69c-c000.snappy.parquet").show
//    val date_dim = sparkSession.read.load("file:///C:\\Users\\WangJiaLi\\Desktop\\shop\\data\\dim_shop.db\\date_dim\\part-00000-2506993f-6a88-4b5a-8f72-1c9806d4ca27-c000.snappy.parquet").show
//    val goods_dim = sparkSession.read.load("file:///C:\\Users\\WangJiaLi\\Desktop\\shop\\data\\dim_shop.db\\goods_dim\\000000_0").show
//    val goods_dim_tmp = sparkSession.read.textFile("file:///C:\\Users\\WangJiaLi\\Desktop\\shop\\data\\dim_shop.db\\goods_dim_tmp\\part-m-00000").show
//    val page_dim = sparkSession.read.load("file:///C:\\Users\\WangJiaLi\\Desktop\\shop\\data\\dim_shop.db\\page_dim\\000000_0").show
//    val region_dim = sparkSession.read.load("file:///C:\\Users\\WangJiaLi\\Desktop\\shop\\data\\dim_shop.db\\region_dim\\part-00000-523e5cb2-931b-4d16-82c7-6b2ca834fe85-c000.snappy.parquet").show
//    val shoper_dim = sparkSession.read.load("file:///C:\\Users\\WangJiaLi\\Desktop\\shop\\data\\dim_shop.db\\shoper_dim\\part-00000-2f5e3a15-8cb1-4252-90c3-7b0a07b4924c-c000.snappy.parquet").sort("id").show

    val a = sparkSession.read.parquet("file:///C:\\myDemo\\shop\\bdp_day=20190630\\156190620000045h5fcd3")
    a.show()

  }
}
