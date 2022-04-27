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
        layer["8mTpzLBfEHgzCXy3DTtW"] = MinecraftNetworkData(
            "Verio",
            "AQUA",
            "YELLOW",
            "DARK_AQUA",
            "https://verio.cc/discord",
            "@VerioNetwork",
            "verio.cc",
            "store.verio.cc"
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

        layer["8mTpzLBfEHgzCXy3DTtW"] = MinecraftNetworkData(
            "Glade",
            "AQUA",
            "YELLOW",
            "DARK_AQUA",
            "discord.gg/fMAEnrxQUJ",
            "@GladeNetwork",
            "glade.gg",
            "store.glade.gg"
        )
    }
}
