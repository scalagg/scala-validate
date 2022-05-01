package gg.scala.validate.plugins

import gg.scala.validate.storage.StorageProvider
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond("gg.scala.validate.plugins.Routing.kt")
        }

        get("/v1/ping/{ping}") {
            val query = call
                .parameters["ping"]?.toLong()
                ?: System.currentTimeMillis()

            call.respond(
                System.currentTimeMillis() - query
            )
        }

        for (provider in StorageProvider.dataProviders) {
            get("/v1/${provider.key}/{id}") {
                val layer = StorageProvider.suppliers[provider.key]

                if (layer == null) {
                    call.respond(
                        gg.scala.validate.model.InternalError("No layer was found.")
                    )
                    return@get
                }

                val fromStorage = layer.layer[call.parameters["id"]]

                if (fromStorage == null) {
                    call.respond(
                        gg.scala.validate.model.InternalError("No result was found.")
                    )
                    return@get
                }

                call.respond(fromStorage)
            }
        }
    }
}
