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

        embeddedServer(Netty, port = 80, host = "0.0.0.0") {
            configureRouting()
            configureDataSupplier()

            StorageProvider.loadMongoResources()
        }.start(wait = true)
    }
}

class KtorValidateArgs(parser: ArgParser) {
    val mongoUri by parser.storing("--mongoUri", help = "MongoDB URI")
}
