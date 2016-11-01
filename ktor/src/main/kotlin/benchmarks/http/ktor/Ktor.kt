package benchmarks.http.ktor

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
        call.respondText("Hello, World!")
      }
    }
  }
}