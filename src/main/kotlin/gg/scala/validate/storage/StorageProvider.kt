package gg.scala.validate.storage

import com.solexgames.datastore.commons.connection.impl.mongo.UriMongoConnection
import com.solexgames.datastore.commons.layer.impl.MongoStorageLayer
import gg.scala.validate.Constants
import gg.scala.validate.provider.DataProvider
import gg.scala.validate.provider.impl.MinecraftNetworkDataProvider
import gg.scala.validate.storage.supplier.StorageSupplier

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
object StorageProvider
{

    val dataProviders = mutableMapOf<String, DataProvider<*>>()
    val layerMap = mutableMapOf<String, MongoStorageLayer<*>>()

    init
    {
        dataProviders["minecraft"] = MinecraftNetworkDataProvider
    }

    fun loadMongoResources()
    {
        val mongoConnection = UriMongoConnection(
            Constants.settings.mongoUri
        )

        dataProviders.forEach {
            layerMap[it.key] = MongoStorageLayer(
                mongoConnection, "Scala", it.key, it.value.getType()
            )
        }
    }

}
