package io.getquill.example.common

import java.util.Date

import io.getquill.MappedEncoding
import org.joda.time.{Duration, LocalDateTime}

trait Encoders {
  implicit val jodaLocalDateTimeEncoder = MappedEncoding[LocalDateTime, Date](_.toDate)

  implicit val jodaDurationEncoder = MappedEncoding[Duration, Long](_.getMillis)
}
