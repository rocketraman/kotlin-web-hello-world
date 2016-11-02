package org.kotlin.community.web.helloworld.sparkjava

import org.kotlin.community.web.helloworld.common.HELLO_WORLD
import spark.Spark.get
import spark.Spark.port

fun main(args: Array<String>) {
  port(8080)
  get("/") { req, res -> HELLO_WORLD }
}