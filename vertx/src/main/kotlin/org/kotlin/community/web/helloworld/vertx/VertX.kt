package org.kotlin.community.web.helloworld.vertx

import io.vertx.core.*
import io.vertx.ext.web.*
import io.vertx.ext.web.handler.*
import org.kotlin.community.web.helloworld.common.*

fun main(args: Array<String>) {
  val vertx = Vertx.vertx()
  val router = Router.router(vertx).apply {
    get("/").handler { request ->
      request.response().putHeader("content-type", "text/html").end(HELLO_WORLD);
    }
    get().handler(StaticHandler.create("public"))
  }

  vertx.createHttpServer().requestHandler {
    router.accept(it)
  }.listen(8080)
}