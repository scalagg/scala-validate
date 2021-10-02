package gg.scala.validate.provide.impl

import gg.scala.validate.model.MinecraftNetworkData
import gg.scala.validate.provide.DataProvider

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
class MinecraftNetworkDataProvider : DataProvider<MinecraftNetworkData>() {

    override fun fetchBasedOnId(id: String): MinecraftNetworkData? {
        return null
    }

    override fun endpoint(): String {
        TODO("Not yet implemented")
    }
}
