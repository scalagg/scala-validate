package gg.scala.validate.templates

/**
 * @author GrowlyX
 * @since 7/30/2022
 */
data class AssetServerTemplates(
    val templates: MutableList<AssetServerTemplate> =
        mutableListOf(AssetServerTemplate())
)

data class AssetServerTemplate(
    val endpoint: String = "templates/test-template.json",
    val authentication: AssetServerAuth = AssetServerAuth(),
    val data: AssetServerData = AssetServerData()
)

data class AssetServerData(
    val inAssetsDirectory: Boolean = true,
    val teamCity: Boolean = false,
    val teamCityAsset: String = "",
    val fileName: String = "test-template.json"
)

data class AssetServerAuth(
    val enabled: Boolean = true,
    val ipWhitelist: List<String> =
        listOf("1.2.3.4")
)
