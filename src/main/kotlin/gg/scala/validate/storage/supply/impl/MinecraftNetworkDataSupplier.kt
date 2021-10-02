package gg.scala.validate.storage.supply.impl

import gg.scala.validate.model.MinecraftNetworkData
import gg.scala.validate.storage.supply.StorageSupplier

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

    override fun lateInit() {
        layer.saveEntry(
            "mcEX7vHSmcQqU35dtRNT5RVA4Qsa73bDWgGbB6juxkbZd",
            MinecraftNetworkData(
                "Scala",
                "GREEN",
                "YELLOW",
                "",
                "scalagg",
                "scala.gg",
                ""
            )
        )

        layer.saveEntry(
            "zrqMgzJ4vmZrnnkHxWENZ7b7XhNzc4CckRkEbsnrJfCd9",
            MinecraftNetworkData(
                "PvPBar",
                "GOLD",
                "YELLOW",
                "discord.gg/pvpbar",
                "pvpbarmc",
                "pvp.bar",
                "store.pvp.bar"
            )
        )
    }
}
