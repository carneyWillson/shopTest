package tableSchema.valueEnum

object target_action  extends Enumeration {
  val Comment_look = Value("comment_look")
  val Attention_goods = Value("attention_goods")
  val Attention_shoper = Value("attention_shoper")
  val Order_commit = Value("order_commit")
  val Order_pay = Value("order_pay")
  val Order_canal = Value("order_canal")
}
