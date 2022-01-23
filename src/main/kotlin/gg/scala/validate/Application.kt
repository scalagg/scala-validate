package gg.scala.validate

import gg.scala.validate.plugins.configureDataSupplier
import gg.scala.validate.plugins.configureRouting
import gg.scala.validate.storage.StorageProvider
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

lateinit var address: String

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 21, host = "0.0.0.0") {
        val ipAddress = URL("http://checkip.amazonaws.com")

        val reader = BufferedReader(
            InputStreamReader(
                ipAddress.openStream()
            )
        )

        address = reader.readLine()

        configureRouting()
        configureDataSupplier()

        StorageProvider.loadMongoResources()
    }.start(wait = true)
}
