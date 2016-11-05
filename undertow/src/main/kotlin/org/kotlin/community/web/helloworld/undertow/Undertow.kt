package org.kotlin.community.web.helloworld.undertow

import io.undertow.*
import io.undertow.Handlers.resource
import io.undertow.server.handlers.resource.*
import io.undertow.util.*

fun main(args: Array<String>) {
  val server = Undertow.builder()
    .addHttpListener(8080, "localhost")
    .setHandler(Handlers.path()
      .addExactPath("/") {
        it.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
        it.responseSender.send("Hello World")
      }
      .addPrefixPath("/", resource(ClassPathResourceManager(Thread.currentThread().contextClassLoader, "public")))
    ).build()
  server.start()
}