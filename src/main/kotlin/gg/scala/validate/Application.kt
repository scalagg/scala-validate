package gg.scala.validate

import com.xenomachina.argparser.ArgParser
import gg.scala.validate.plugins.configureDataSupplier
import gg.scala.validate.plugins.configureRouting
import gg.scala.validate.storage.StorageProvider
import io.ktor.server.engine.*
import io.ktor.server.netty.*

lateinit var mongoUri: String
val storageProvider = StorageProvider()

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::KtorValidateArgs).let {
        mongoUri = it.mongoUri

        embeddedServer(Netty, port = it.port.toInt(), host = it.host) {
            configureRouting()
            configureDataSupplier()

            println("Monkey v1.0.0 has been enabled.")
        }.start(wait = true)
    }
}

class KtorValidateArgs(parser: ArgParser) {

    val host by parser.storing("--host", help = "Ktor Host")
    val port by parser.storing("--port", help = "Ktor Port")

    val mongoUri by parser.storing("--mongoUri", help = "MongoDB URI")

}
