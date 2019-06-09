package tableSchema.valueEnum

object action extends Enumeration{
  val Install = Value("install")
  val Launch = Value("launch")
  val Login = Value("login")
  val Register = Value("register")
  val Interactive = Value("interactive")
  val Page_enter_h5 = Value("page_enter_h5")
  val Page_enter_native = Value("page_enter_native")
  val Exit = Value("exit")
}
