package org.kotlin.community.web.helloworld.nanohttpd

import fi.iki.elonen.NanoHTTPD
import org.kotlin.community.web.helloworld.common.HELLO_WORLD

fun main(args: Array<String>) {
  val server = object : NanoHTTPD(8080) {
    override fun serve(session: IHTTPSession): Response = newFixedLengthResponse(HELLO_WORLD)
  }
  server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
}
