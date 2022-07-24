package gg.scala.validate.plugins

import gg.scala.validate.storage.StorageProvider
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File
import kotlin.random.Random

fun Application.configureRouting()
{
    routing {
        get("/") {
            call.respond("gg.scala.validate.plugins.Routing.kt")
        }

        val file = File(
            "assets.zip"
        )

        get("/minequest/ZNhIaDhQUP/assets.zip")
        {
            call.respondFile(file)
        }

        get("/v1/client/pinned")
        {
            call.respond(
                listOf(
                    mapOf(
                        "name" to "Scala Dev",
                        "address" to "dev.scala.gg"
                    ),
                    mapOf(
                        "name" to "Pot PvP",
                        "address" to "tropic.gg"
                    )
                )
            )
        }

        get("/v1/ping/{ping}") {
            kotlin.runCatching {
                val requested = call
                    .parameters["ping"]?.toLong()
                    ?: System.currentTimeMillis()

                call.respond(
                    System.currentTimeMillis() - requested
                )
            }.onFailure {
                call.respond(
                    "69".repeat(
                        Random.nextInt(2, 10)
                    )
                )
            }
        }

        for (provider in StorageProvider.dataProviders)
        {
            get("/v1/${provider.key}/{id}") {
                val layer = StorageProvider.suppliers[provider.key]

                if (layer == null)
                {
                    call.respond(
                        gg.scala.validate.model.InternalError("No layer was found.")
                    )
                    return@get
                }

                val fromStorage = layer.layer[call.parameters["id"]]

                if (fromStorage == null)
                {
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
