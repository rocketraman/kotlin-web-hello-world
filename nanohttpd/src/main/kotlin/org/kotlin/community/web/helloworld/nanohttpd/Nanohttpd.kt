package org.kotlin.community.web.helloworld.nanohttpd

import fi.iki.elonen.NanoHTTPD
import org.kotlin.community.web.helloworld.common.HELLO_WORLD

fun main(args: Array<String>) {
  val server = object : NanoHTTPD(8080) {
    override fun serve(session: IHTTPSession): Response = when (session.uri) {
      "/" -> newFixedLengthResponse(HELLO_WORLD)
      else -> newFixedLengthResponse(Response.Status.NOT_FOUND, NanoHTTPD.MIME_PLAINTEXT, "Not found")
    }
  }
  server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
}
