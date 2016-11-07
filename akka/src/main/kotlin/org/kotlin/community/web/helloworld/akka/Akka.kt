package org.kotlin.community.web.helloworld.akka

import akka.actor.*
import akka.http.javadsl.*
import akka.http.javadsl.model.*
import akka.http.javadsl.server.*
import akka.http.javadsl.server.Directives.*
import akka.stream.*
import org.kotlin.community.web.helloworld.common.*

fun main(args: Array<String>) {
  val system = ActorSystem.create()
  val materializer = ActorMaterializer.create(system)

  val routeFlow = Akka.createRoute().flow(system, materializer)
  val binding = Http.get(system).bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer)
}

object Akka {
  fun createRoute(): Route {
    return get {
      route(
        // matches the empty path
        pathSingleSlash {
          // return a constant string with a certain content type
          complete(HttpEntities.create(ContentTypes.TEXT_PLAIN_UTF8, HELLO_WORLD))
        },
        path("ping") {
          // return a simple `text/plain` response
          complete("PONG!")
        },
        path("image.png") {
          getFromResource("public/image.png")
        }
      )
    }
  }
}