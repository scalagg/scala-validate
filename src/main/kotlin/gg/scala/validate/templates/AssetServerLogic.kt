package gg.scala.validate.templates

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import java.io.File
import java.nio.file.Files

/**
 * @author GrowlyX
 * @since 7/30/2022
 */
object AssetServerLogic
{
    val templates = mutableListOf<AssetServerTemplate>()

    private val gson: Gson = GsonBuilder()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .setPrettyPrinting()
        .create()

    fun configure()
    {
        val templates = File("assets.json")
        val templatesPath = templates.toPath()

        if (!templates.exists())
        {
            templates.createNewFile()

            Files.write(
                templatesPath,
                gson.toJson(AssetServerTemplates())
                    .encodeToByteArray()
            )
        }

        val deserialized = gson
            .fromJson(
                Files.newBufferedReader(templatesPath),
                AssetServerTemplates::class.java
            )

        val assetsDirectory = File("assets")

        if (!assetsDirectory.exists())
        {
            assetsDirectory.mkdirs()
        }

        this.templates.addAll(deserialized.templates)
    }
}
