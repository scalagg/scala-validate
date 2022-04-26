package gg.scala.validate.storage

import gg.scala.validate.provider.DataProvider
import gg.scala.validate.provider.impl.MinecraftNetworkDataProvider
import gg.scala.validate.storage.supplier.StorageSupplier
import gg.scala.validate.storage.supplier.impl.MinecraftNetworkDataSupplier

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
object StorageProvider
{
    val dataProviders = mutableMapOf<String, DataProvider<*>>()
    val suppliers = mutableMapOf<String, StorageSupplier<*>>()

    init
    {
        dataProviders["minecraft"] = MinecraftNetworkDataProvider
        suppliers["minecraft"] = MinecraftNetworkDataSupplier
    }
}
