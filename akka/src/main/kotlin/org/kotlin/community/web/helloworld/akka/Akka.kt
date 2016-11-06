package org.kotlin.community.web.helloworld.akka

import akka.actor.*
import akka.http.javadsl.*
import akka.http.javadsl.model.*
import akka.japi.function.*
import akka.stream.*
import akka.stream.javadsl.*
import org.kotlin.community.web.helloworld.common.*

val NOT_FOUND = HttpResponse.create().withStatus(404).withEntity("Unknown resource!")

fun main(args: Array<String>) {
  val system = ActorSystem.create()
  val materializer = ActorMaterializer.create(system)

  val serverSource = Http.get(system).bind(ConnectHttp.toHost("localhost", 8080), materializer)

  val requestHandler = Function<HttpRequest, HttpResponse> { request ->
    val uri = request.uri
    if (request.method() == HttpMethods.GET) {
      when (uri.path()) {
        "/" -> HttpResponse.create().withEntity(ContentTypes.TEXT_PLAIN_UTF8, HELLO_WORLD)
        else -> NOT_FOUND
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