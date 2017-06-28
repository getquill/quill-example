package io.getquill.example.genericInsertOrUpdate

import io.getquill.context.jdbc.JdbcContext

import scala.language.experimental.macros

trait Queries {
  this: JdbcContext[_, _] =>
  def insertOrUpdate[T](entity: T, filter: (T) => Boolean): Unit = macro InsertOrUpdateMacro.insertOrUpdate[T]
}
