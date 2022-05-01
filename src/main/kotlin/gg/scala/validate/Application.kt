package gg.scala.validate

import gg.scala.validate.plugins.configureDataSupplier
import gg.scala.validate.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(
        Netty, port = 8080,
        host = "46.105.133.79"
    ) {
        configureRouting()
        configureDataSupplier()
    }.start(wait = true)
}
