package gg.scala.validate.plugins

import gg.scala.validate.storageProvider
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        for (provider in storageProvider.dataProviders) {
            get("/routing/${provider.key}/{id}") {
                val layer = storageProvider.layerMap[provider.key]

                if (layer == null) {
                    call.respond(HttpStatusCode.Conflict, "An internal error occurred while fetching the result.")
                    return@get
                }

                val fromStorage = layer.fetchEntryByKeySync(
                    call.parameters["id"]
                )

                if (fromStorage == null) {
                    call.respond(HttpStatusCode.NotFound, "No result was found.")
                    return@get
                }

                call.respond(fromStorage)
            }
        }
    }
}