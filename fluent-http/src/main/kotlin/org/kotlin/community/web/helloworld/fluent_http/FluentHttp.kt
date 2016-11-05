package org.kotlin.community.web.helloworld.fluent_http

import net.codestory.http.*
import net.codestory.http.io.*
import org.kotlin.community.web.helloworld.common.*


fun main(args: Array<String>) {
  WebServer().configure { routes ->
    routes.get("/", HELLO_WORLD)
    routes.get("/image.png", ClassPaths.getResource("public/image.png"))
  }.start()
}

