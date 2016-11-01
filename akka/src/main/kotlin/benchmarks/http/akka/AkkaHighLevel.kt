package benchmarks.http.akka

import akka.actor.ActorSystem
import akka.http.javadsl.ConnectHttp
import akka.http.javadsl.Http
import akka.http.javadsl.model.ContentTypes
import akka.http.javadsl.model.HttpEntities
import akka.http.javadsl.server.Directives.*
import akka.http.javadsl.server.Route
import akka.stream.ActorMaterializer

fun main(args: Array<String>) {
  val system = ActorSystem.create()
  val materializer = ActorMaterializer.create(system)

  val routeFlow = AkkaHighLevel.createRoute().flow(system, materializer)
  val binding = Http.get(system).bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer)
}

object AkkaHighLevel {
  fun createRoute(): Route {
    return get { route(
      // matches the empty path
      pathSingleSlash {
        // return a constant string with a certain content type
        complete(HttpEntities.create(ContentTypes.TEXT_PLAIN_UTF8, "Hello world!"))
      },
      path("ping") {
        // return a simple `text/plain` response
        complete("PONG!")
      }
    )}
  }
}