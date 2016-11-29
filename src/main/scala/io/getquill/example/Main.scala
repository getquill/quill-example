package io.getquill.example

import io.getquill.example.postgres.async.runExample

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {
  Await.result(runExample(), Duration.Inf)
}
