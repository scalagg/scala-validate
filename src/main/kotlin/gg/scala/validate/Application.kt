package gg.scala.validate

import gg.scala.validate.plugins.configureDataSupplier
import gg.scala.validate.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(
        Netty, port = 21, host = "0.0.0.0"
    ) {
        configureRouting()
        configureDataSupplier()
    }.start(wait = true)
}
