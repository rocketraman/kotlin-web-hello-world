package benchmarks.http.ktor

import benchmarks.http.common.HELLO_WORLD
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.features.*
import org.jetbrains.ktor.logging.*
import org.jetbrains.ktor.response.*
import org.jetbrains.ktor.routing.*

class Ktor : ApplicationModule() {
  override fun Application.install() {
    install(DefaultHeaders)
//    install(CallLogging)
    install(Routing) {
      get("/") {
        call.respondText(HELLO_WORLD)
      }
    }
  }
}