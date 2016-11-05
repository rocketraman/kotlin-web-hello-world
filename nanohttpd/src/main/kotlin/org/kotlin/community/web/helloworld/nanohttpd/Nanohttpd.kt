package org.kotlin.community.web.helloworld.nanohttpd

import fi.iki.elonen.*
import org.kotlin.community.web.helloworld.common.*

fun main(args: Array<String>) {
  val server = object : NanoHTTPD(8080) {
    override fun serve(session: IHTTPSession): Response = when (session.uri) {
      "/" -> newFixedLengthResponse(HELLO_WORLD)
      "/image.png" -> {
        val resource = Thread.currentThread().contextClassLoader.getResource("image.png")
        newChunkedResponse(Response.Status.OK, "image/png", resource.openStream())
      }
      else -> newFixedLengthResponse(Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Not found")
    }
  }
  server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
}
