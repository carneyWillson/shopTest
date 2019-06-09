package constant

/**
  * 用于记录数据抽取过程中, 新增列的列名
  * 下述列名在他们各自的源表中没有出现, 所以无法依靠血缘关系进行列的命名
  * 因此使用此类来记录
  */
object newColumn {
  /****** ========== 新出现的列名 ========== ******/
  final val Good_id_from_Target_ids = "good_id"
  final val Target_id_from_Target_ids = "target_id"
  final val Target_type_from_Pay_type = "target_type"
}
