package gg.scala.validate.provide.impl

import gg.scala.validate.model.MinecraftNetworkData
import gg.scala.validate.provide.DataProvider

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
object MinecraftNetworkDataProvider : DataProvider<MinecraftNetworkData>() {

    override fun getType(): Class<MinecraftNetworkData> = MinecraftNetworkData::class.java

}
