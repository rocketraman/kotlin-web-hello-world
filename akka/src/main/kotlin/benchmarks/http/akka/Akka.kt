package benchmarks.http.akka

import akka.actor.ActorSystem
import akka.http.javadsl.ConnectHttp
import akka.http.javadsl.Http
import akka.http.javadsl.model.ContentTypes
import akka.http.javadsl.model.HttpMethods
import akka.http.javadsl.model.HttpRequest
import akka.http.javadsl.model.HttpResponse
import akka.japi.function.Function
import akka.stream.ActorMaterializer
import akka.stream.javadsl.Sink

val NOT_FOUND = HttpResponse.create().withStatus(404).withEntity("Unknown resource!")

fun main(args: Array<String>) {
  val system = ActorSystem.create()
  val materializer = ActorMaterializer.create(system)

  val serverSource = Http.get(system).bind(ConnectHttp.toHost("localhost", 8080), materializer)

  val requestHandler = Function<HttpRequest, HttpResponse> { request ->
    val uri = request.uri
    if (request.method() == HttpMethods.GET) {
      if (uri.path() == "/") {
        HttpResponse.create()
          .withEntity(ContentTypes.TEXT_PLAIN_UTF8,
            "Hello world!")
      } else {
        NOT_FOUND
      }
    } else {
      NOT_FOUND
    }
  }

  val serverBindingFuture = serverSource.to(Sink.foreach { connection ->
    connection.handleWithSyncHandler(requestHandler, materializer)
    // this is equivalent to
    //connection.handleWith(Flow.of(HttpRequest.class).map(requestHandler), materializer);
  }).run(materializer)
}