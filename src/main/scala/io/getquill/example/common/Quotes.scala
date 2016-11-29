package io.getquill.example.common

import io.getquill.context.sql.SqlContext
import org.joda.time.LocalDateTime

trait Quotes {
  this: SqlContext[_, _] =>

  implicit class LocalDateTimeQuotes(left: LocalDateTime) {
    def >(right: LocalDateTime) = quote(infix"$left > $right".as[Boolean])

    def <(right: LocalDateTime) = quote(infix"$left < $right".as[Boolean])

    def >=(right: LocalDateTime) = quote(infix"$left >= $right".as[Boolean])

    def <=(right: LocalDateTime) = quote(infix"$left <= $right".as[Boolean])
  }
}
