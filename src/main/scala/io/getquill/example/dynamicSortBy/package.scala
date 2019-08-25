package io.getquill.example

import io.getquill.{Literal, MirrorContext, MirrorSqlDialect}

package object dynamicSortBy {
  val ctx = new MirrorContext[MirrorSqlDialect, Literal]
  case class Person(id: Int, name: String, age: Int)
  import ctx._

  def sort[A](f: Person => A)
}
