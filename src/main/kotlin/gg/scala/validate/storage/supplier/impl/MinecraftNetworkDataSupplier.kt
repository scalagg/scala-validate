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
            "AQUA",
            "DARK_AQUA",
            "GRAY",
            "https://discord.gg/3xamrcrZUP",
            "@MinequestGG",
            "minequest.gg",
            "store.minequest.gg"
        )

        layer["8mTpzLBfEHgzCXy3DTtW"] = MinecraftNetworkData(
            "Tropic",
            "GOLD",
            "YELLOW",
            "GRAY",
            "tropic.gg/discord",
            "@PlayTropic",
            "tropic.gg",
            "store.tropic.gg"
        )

        layer["dewgs"] = MinecraftNetworkData(
            "Duels",
            "AQUA",
            "WHITE",
            "DARK_AQUA",
            "https://discord.gg/BFt8m5UCch",
            "@DuelsUS",
            "duels.us",
            "store.duels.us"
        )

        layer["mcEX7vHSmcQqU35dtRNT5RVA4Qsa73bDWgGbB6juxkbZd"] = MinecraftNetworkData(
            "Scala",
            "GOLD",
            "YELLOW",
            "GRAY",
            "scala.gg/discord",
            "scalagg",
            "scala.gg",
            "store.scala.gg"
        )

        layer["factions"] = MinecraftNetworkData(
            "Factions",
            "AQUA",
            "DARK_AQUA",
            "GRAY",
            "discord.gg/factions",
            "@CFac",
            "factions.server.scala.gg",
            "store.factions.server.scala.gg"
        )

        layer["ms3vCu3JaybCz0ATX9HUUHZe5P0ZUNXh"] = MinecraftNetworkData(
            "Glade",
            "AQUA",
            "DARK_AQUA",
            "GRAY",
            "discord.gg/fMAEnrxQUJ",
            "@GladeNetwork",
            "glade.gg",
            "store.glade.gg"
        )

        layer["FvqE2JjH7rUCWfKb7ZhhkCgerLfnYbV6G"] = MinecraftNetworkData(
            "Esta Land",
            "RED",
            "YELLOW",
            "GRAY",
            "https://discord.gg/jkKGRymQxQ",
            "@EstaLandNetwork",
            "esta.land",
            "store.esta.land"
        )

        layer["solara"] = MinecraftNetworkData(
            "Solara",
            "YELLOW",
            "GRAY",
            "WHITE",
            "https://discord.gg/nfSFFJsDmU",
            "@playsolara",
            "solara.gg",
            "shop.solara.gg"
        )
    }
}
