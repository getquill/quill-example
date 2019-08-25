package io.getquill.example

import io.getquill.{PostgresJdbcContext, SnakeCase}

package object genericInsertOrUpdate {
  val ctx = new PostgresJdbcContext[SnakeCase]("jdbc.postgres") with Queries

  def example1(): Unit = {
    val inserting = Person(1, "")
    ctx.insertOrUpdate(inserting, (p: Person) => p.name == "")
  }

  def example2(): Unit = {
    import ctx._
    query[Person].sort
    val inserting = Person(1, "")
    ctx.insertOrUpdate(inserting, (p: Person) => p.name == lift(inserting.name))
  }
}
