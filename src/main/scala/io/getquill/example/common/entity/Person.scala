package io.getquill.example.common.entity

import org.joda.time.LocalDateTime

case class Person(id: Int, name: String, birthDate: LocalDateTime, deathDate: Option[LocalDateTime])
