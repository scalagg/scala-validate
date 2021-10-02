package gg.scala.validate.model

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
data class MinecraftNetworkData(
    val serverName: String,

    val primary: String,
    val secondary: String,

    val discord: String,
    val twitter: String,
    val domain: String,
    val store: String,
)
