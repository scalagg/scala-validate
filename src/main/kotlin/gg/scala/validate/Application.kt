package gg.scala.validate

import com.xenomachina.argparser.ArgParser
import gg.scala.validate.plugins.configureDataSupplier
import gg.scala.validate.plugins.configureRouting
import gg.scala.validate.storage.StorageProvider
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::KtorValidateArgs).let {
        Constants.settings = it

        embeddedServer(Netty, port = it.port.toInt(), host = it.host) {
            configureRouting()
            configureDataSupplier()

            StorageProvider.loadMongoResources()
        }.start(wait = true)
    }
}

class KtorValidateArgs(parser: ArgParser) {

    val host by parser.storing("--host", help = "Ktor Host")
    val port by parser.storing("--port", help = "Ktor Port")

    val mongoUri by parser.storing("--mongoUri", help = "MongoDB URI")

}
