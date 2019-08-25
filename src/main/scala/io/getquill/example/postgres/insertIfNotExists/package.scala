package io.getquill.example.postgres

import io.getquill.{PostgresJdbcContext, SnakeCase}

package object insertIfNotExists {
  val ctx = new PostgresJdbcContext[SnakeCase]("jdbc.postgres")
  import ctx._

  implicit def a(t: (Int, String)): Person = (Person.apply _).tupled(t)

  def runExample(): Unit = {
    val _ = ctx.run(
      quote {
      query[Person].filter(p => query[Person].filter(p2 => p2.name == "Michael").isEmpty).map(p => (1, "Michael"))
      }.foreach(p => query[Person].insert(p)))
  }
}
