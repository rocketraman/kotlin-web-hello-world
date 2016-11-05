package org.kotlin.community.web.helloworld.ktor

import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.content.*
import org.jetbrains.ktor.features.*
import org.jetbrains.ktor.response.*
import org.jetbrains.ktor.routing.*
import org.kotlin.community.web.helloworld.common.*

class Ktor : ApplicationModule() {
  override fun Application.install() {
    install(DefaultHeaders)
    install(Routing) {
      get("/") {
        call.respondText(HELLO_WORLD)
      }
      serveClasspathResources("public")
    }
  }
}