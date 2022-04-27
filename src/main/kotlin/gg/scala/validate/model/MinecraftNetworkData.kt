package gg.scala.validate.model

import kotlinx.serialization.Serializable

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
@Serializable
data class MinecraftNetworkData(
    val serverName: String,

    val primary: String,
    val secondary: String,
    val neutral: String,

    val discord: String,
    val twitter: String,
    val domain: String,
    val store: String
)
