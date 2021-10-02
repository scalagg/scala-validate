package gg.scala.validate

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        Netty, port = 8080, host = "0.0.0.0"
    ) {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/json/kotlinx-serialization") {
                call.respond(mapOf("hello" to "world"))
            }
        }
    }.start(wait = true)
}
