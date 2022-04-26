package gg.scala.validate.plugins

import gg.scala.validate.storage.StorageProvider
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        for (provider in StorageProvider.dataProviders) {
            get("/routing/${provider.key}/{id}") {
                val layer = StorageProvider.suppliers[provider.key]

                if (layer == null) {
                    call.respond(HttpStatusCode.Conflict, "An internal error occurred while fetching the result.")
                    return@get
                }

                val fromStorage = layer.layer[call.parameters["id"]]

                if (fromStorage == null) {
                    call.respond(HttpStatusCode.NotFound, "No result was found.")
                    return@get
                }

                call.respond(fromStorage)
            }
        }
    }
}
