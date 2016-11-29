package io.getquill.example.common

import java.util.Date

import io.getquill.MappedEncoding
import org.joda.time.{Duration, LocalDateTime}

trait Decoders {
  implicit val jodaLocalDateTimeDecoder = MappedEncoding[Date, LocalDateTime](LocalDateTime.fromDateFields)

  implicit val jodaDurationDecoder = MappedEncoding(Duration.millis)
}
