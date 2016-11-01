package benchmarks.http.vertx

import io.vertx.core.Vertx

fun main(args: Array<String>) {
  val vertx = Vertx.vertx()
  val server = vertx.createHttpServer()
  server.requestHandler { request ->

    // This handler gets called for each request that arrives on the server
    val response = request.response()
    response.putHeader("content-type", "text/plain")

    // Write to the response and end it
    response.end("Hello World!")
  }

  server.listen(8080)
}