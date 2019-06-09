package util

object Implicit {

  // 将枚举类, 转为其values对应的Seq
  implicit def setToSeq[T<: Enumeration](valueSet: T) = {
    valueSet.values.toSeq.map(_.toString)
  }
}
