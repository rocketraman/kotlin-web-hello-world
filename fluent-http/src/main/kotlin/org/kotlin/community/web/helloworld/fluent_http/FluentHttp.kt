package org.kotlin.community.web.helloworld.fluent_http

import net.codestory.http.WebServer
import org.kotlin.community.web.helloworld.common.HELLO_WORLD


fun main(args: Array<String>) {
  WebServer().configure { routes -> routes.get("/", HELLO_WORLD) }.start()  
}

