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
            "8mTpzLBfEHgzCXy3DTtW",
            MinecraftNetworkData(
                "Verio",
                "AQUA",
                "YELLOW",
                "https://verio.cc/discord",
                "@VerioNetwork",
                "verio.cc",
                "shop.verio.cc"
            )
        )
    }
}
