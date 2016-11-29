package io.getquill.example.postgres

import io.getquill.example.common.{Decoders, Encoders, Quotes}
import io.getquill.example.common.entity.Person
import io.getquill.{PostgresAsyncContext, SnakeCase}
import org.joda.time.{DateTimeZone, LocalDateTime}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

package object async {
  val ctx = new PostgresAsyncContext[SnakeCase]("async.postgres") with Encoders with Decoders with Quotes

  import ctx._

  def create(person: Person): Future[Long] =
    ctx.run(query[Person].insert(lift(person)))

  def read(id: Int): Future[Seq[Person]] =
    ctx.run(query[Person].filter(_.id == lift(id)))

  def readNamesOfPersonsOlderThen(age: Int): Future[Seq[String]] =
    ctx.run(query[Person]
      .filter(t => t.birthDate > lift(LocalDateTime.now(DateTimeZone.UTC).minusYears(age)) && t.deathDate.isEmpty)
      .map(_.name))

  def update(person: Person): Future[Long] =
    ctx.run(query[Person].update(lift(person)))

  def update(id: Int, name: String): Future[Long] =
    ctx.run(query[Person].filter(_.id == lift(id)).update(_.name -> lift(name)))

  def delete(id: Int): Future[Long] =
    ctx.run(query[Person].filter(_.id == lift(id)).delete)

  def runExample(): Future[Unit] =
    create(Person(1, "David", new LocalDateTime(1947, 1, 8, 0, 0), None)).flatMap { _ =>
      read(1)
    }.flatMap { _ =>
      readNamesOfPersonsOlderThen(30)
    }.flatMap { _ =>
      update(Person(1, "David", new LocalDateTime(1947, 1, 8, 0, 0), Some(new LocalDateTime(2016, 1, 10, 0, 0))))
    }.flatMap { _ =>
      update(1, "David Bowie")
    }.flatMap { _ =>
      delete(1)
    }.map(_ => ())
}
