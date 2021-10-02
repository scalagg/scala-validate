package gg.scala.validate

import kotlinx.serialization.Serializable

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
object ValidateConstants {

    @JvmStatic
    lateinit var args: KtorValidateArgs

    @JvmStatic
    val INVALID_NO_RESULT = Response(
        "INVALID",
        "No result was found."
    )

    @JvmStatic
    val INVALID_NO_LAYER = Response(
        "INVALID",
        "An internal error occurred while fetching the result."
    )

    @Serializable
    data class Response(
        val result: String,
        val message: String
    )
}
