package org.kotlin.community.web.helloworld.wasabi

import org.kotlin.community.web.helloworld.common.*
import org.wasabifx.wasabi.app.*

fun main(args: Array<String>) {
  val server = AppServer(AppConfiguration(port = 8080, enableLogging = false))

  server.get("/", {
    response.contentType = "text/plain"
    response.send(HELLO_WORLD)
  })

  server.get("/image.png", {
    val resource = Thread.currentThread().contextClassLoader.getResource("public/image.png")
    response.sendFile(resource.path, "image/png")
  })

  server.start()
}