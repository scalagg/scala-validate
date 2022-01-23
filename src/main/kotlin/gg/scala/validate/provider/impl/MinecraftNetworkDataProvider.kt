package gg.scala.validate.provider.impl

import gg.scala.validate.model.MinecraftNetworkData
import gg.scala.validate.provider.DataProvider

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
object MinecraftNetworkDataProvider : DataProvider<MinecraftNetworkData>() {
    override fun getType(): Class<MinecraftNetworkData> = MinecraftNetworkData::class.java
}
