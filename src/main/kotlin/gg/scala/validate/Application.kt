package gg.scala.validate

import gg.scala.validate.plugins.configureDataSupplier
import gg.scala.validate.plugins.configureRouting
import gg.scala.validate.templates.AssetServerLogic
import io.ktor.application.install
import io.ktor.features.XForwardedHeaderSupport
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    AssetServerLogic.configure()

    embeddedServer(
        Netty,
        port = args[1].toInt(),
        host = args[0]
    ) {
        install(XForwardedHeaderSupport)

        configureRouting()
        configureDataSupplier()
    }.start(wait = true)
}
