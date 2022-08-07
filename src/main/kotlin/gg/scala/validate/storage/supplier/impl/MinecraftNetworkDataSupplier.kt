package gg.scala.validate.storage.supplier.impl

import gg.scala.validate.model.MinecraftNetworkData
import gg.scala.validate.storage.supplier.StorageSupplier

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
object MinecraftNetworkDataSupplier : StorageSupplier<MinecraftNetworkData>() {

    override fun getType(): Class<MinecraftNetworkData> {
        return MinecraftNetworkData::class.java
    }

    override fun getId(): String {
        return "minecraft"
    }

    init
    {
        layer["EiojmWEOIDJmkwedjm"] = MinecraftNetworkData(
            "Minequest",
            "DARK_AQUA",
            "YELLOW",
            "GRAY",
            "https://discord.gg/minequestgg",
            "@MinequestGG",
            "minequest.gg",
            "store.minequest.gg"
        )

        layer["mcEX7vHSmcQqU35dtRNT5RVA4Qsa73bDWgGbB6juxkbZd"] = MinecraftNetworkData(
            "Scala",
            "GREEN",
            "YELLOW",
            "GRAY",
            "scala.gg/discord",
            "scalagg",
            "scala.gg",
            "store.scala.gg"
        )

        layer["WELPRkfpwerijmferikwJMF"] = MinecraftNetworkData(
            "Muffinized",
            "YELLOW",
            "WHITE",
            "GRAY",
            "discord.muffinized.net",
            "Muffinized",
            "muffinized.net",
            "store.muffinized.net"
        )

        layer["8mTpzLBfEHgzCXy3DTtW"] = MinecraftNetworkData(
            "Tropic",
            "GOLD",
            "YELLOW",
            "GREEN",
            "tropic.gg/discord",
            "@PlayTropic",
            "tropic.gg",
            "store.tropic.gg"
        )
    }
}
