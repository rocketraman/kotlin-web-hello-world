package org.kotlin.community.web.helloworld.sparkjava

import org.kotlin.community.web.helloworld.common.*
import spark.Spark.*

fun main(args: Array<String>) {
  port(8080)
  staticFileLocation("/public");
  get("/") { req, res -> HELLO_WORLD }
}