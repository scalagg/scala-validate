package gg.scala.validate.plugins

import gg.scala.validate.storage.StorageProvider
import gg.scala.validate.storage.supplier.impl.MinecraftNetworkDataSupplier
import gg.scala.validate.templates.AssetServerLogic
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.routing.get
import io.ktor.routing.routing
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.FileTime
import java.time.Instant
import kotlin.random.Random

fun Application.configureRouting()
{
    routing {
        var registeredAssets = 0

        get("/") {
            call.respond(
                mapOf(
                    "assets" to mapOf(
                        "status" to "OPERATIONAL",
                        "count" to "$registeredAssets"
                    ),
                    "minecraft" to mapOf(
                        "status" to "OPERATIONAL",
                        "count" to "${MinecraftNetworkDataSupplier.layer.size}"
                    )
                )
            )
        }

        val logic = AssetServerLogic

        for (template in logic.templates)
        {
            registeredAssets += 1

            val temp = File(template.data.fileName)

            if (temp.exists())
            {
                Files.setLastModifiedTime(
                    temp.toPath(), FileTime.from(Instant.now())
                )
            }

            if (template.data.teamCity && template.data.teamCityMetadataEndpoint != null)
            {
                get("/metadata/${template.data.teamCityMetadataEndpoint}") {
                    var fileName = template.data.fileName

                    this.call.parameters.forEach { s, strings ->
                        val firstOrNull = strings.firstOrNull()
                            ?: return@forEach

                        fileName = fileName.replace(
                            "{$s}", firstOrNull
                        )
                    }

                    val directory = File(fileName)
                    val assetDirectory = directory.listFiles()!!
                        .filter { it.isDirectory }
                        .maxByOrNull {
                            it.name.toInt()
                        }
                        ?: return@get call.respond(HttpStatusCode.NotFound)

                    call.respond(assetDirectory.name)
                }
            }

            get(
                "/delivery/${
                    if (template.authentication.enabled) "auth/" else ""
                }${template.endpoint}"
            )
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

                var fileName = template.data.fileName

                this.call.parameters.forEach { s, strings ->
                    val firstOrNull = strings.firstOrNull()
                        ?: return@forEach

                    fileName = fileName.replace(
                        "{$s}", firstOrNull
                    )
                }

                var asset = if (template.data.inAssetsDirectory)
                {
                    File("assets", fileName)
                } else
                {
                    File(fileName)
                }

                if (template.data.teamCity)
                {
                    val directory = File(fileName)
                    val assetDirectory = directory.listFiles()!!
                        .filter { it.isDirectory }
                        .maxByOrNull {
                            it.name.toInt()
                        }
                        ?: return@get call.respond(
                            HttpStatusCode.NoContent,
                            mapOf("description" to "Asset was not found internally.")
                        )

                    asset = File(assetDirectory, template.data.teamCityAsset)

                    println("[Assets] Using build number: ${asset.parent}")
                }

                if (!asset.exists())
                {
                    call.respond(
                        HttpStatusCode.NoContent,
                        mapOf("description" to "Asset was not found internally.")
                    )
                    return@get
                }

                call.respondFile(asset) {
                    println("[Assets] Delivering ${template.data.fileName} to $address...")
                }
            }
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
