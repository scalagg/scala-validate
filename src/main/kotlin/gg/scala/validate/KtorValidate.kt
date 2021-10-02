package gg.scala.validate

import com.xenomachina.argparser.ArgParser
import gg.scala.validate.storage.StorageProvider
import gg.scala.validate.storage.supply.impl.MinecraftNetworkDataSupplier
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::KtorValidateArgs).let {
        embeddedServer(
            Netty,
            port = it.port.toInt(),
            host = it.host
        ) {
            install(ContentNegotiation) {
                json()
            }

            ValidateConstants.args = it

            StorageProvider.initialLoad()
            MinecraftNetworkDataSupplier.init()

            routing {
                for (provider in StorageProvider.dataProviders) {
                    get("/routing/${provider.key}/{id}") {
                        val layer = StorageProvider.layerMap[provider.key]

                        if (layer == null) {
                            call.respond(ValidateConstants.INVALID_NO_LAYER)
                            return@get
                        }

                        val fromStorage = layer.fetchEntryByKeySync(
                            call.parameters["id"]
                        )

                        call.respond(fromStorage ?: ValidateConstants.INVALID_NO_RESULT)
                    }
                }

                // TODO: 10/2/2021 add post handling w/ security
            }
        }.start(wait = true)
    }
}

class KtorValidateArgs(parser: ArgParser) {

    val host by parser.storing("--host", help = "Ktor Host")
    val port by parser.storing("--port", help = "Ktor Port")

    val mongoUri by parser.storing("--mongoUri", help = "MongoDB URI")

}
