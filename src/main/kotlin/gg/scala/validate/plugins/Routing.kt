package gg.scala.validate.plugins

import gg.scala.validate.storage.StorageProvider
import gg.scala.validate.templates.AssetServerLogic
import io.ktor.application.*
import io.ktor.features.origin
import io.ktor.http.HttpStatusCode
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

        val logic = AssetServerLogic

        for (template in logic.templates)
        {
            val asset = File(
                "assets", template.data.fileName
            )

            if (!asset.exists())
            {
                println("[Assets] ${asset.name} does not exist! Not publishing this route.")
                continue
            }

            get("/delivery/${
                if (template.authentication.enabled) "auth/" else ""
            }${template.endpoint}")
            {
                val address = call.request.headers["X-Forwarded-For"]
                println("[Assets] $address is accessing ${template.endpoint}")

                if (template.authentication.enabled)
                {
                    val whitelisted = template
                        .authentication.ipWhitelist

                    if (!whitelisted.contains(address))
                    {
                        return@get call.respond(
                            HttpStatusCode.Unauthorized,
                            mapOf("description" to "You are not permitted to view this asset.")
                        )
                    }
                }

                call.respondFile(asset) {
                    println("[Assets] Delivering ${template.data.fileName} to $address...")
                }
            }
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
