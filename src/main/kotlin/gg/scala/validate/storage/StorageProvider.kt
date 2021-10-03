package gg.scala.validate.storage

import com.solexgames.datastore.commons.connection.impl.mongo.UriMongoConnection
import com.solexgames.datastore.commons.layer.impl.MongoStorageLayer
import gg.scala.validate.mongoUri
import gg.scala.validate.provider.DataProvider
import gg.scala.validate.provider.impl.MinecraftNetworkDataProvider
import gg.scala.validate.storage.supplier.StorageSupplier

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
class StorageProvider {

    val dataProviders = mutableMapOf<String, DataProvider<*>>()
    val storageSuppliers = mutableMapOf<String, StorageSupplier<*>>()

    val layerMap = mutableMapOf<String, MongoStorageLayer<*>>()

    init {
        dataProviders["minecraft"] = MinecraftNetworkDataProvider

        loadMongoResources()
    }

    private fun loadMongoResources() {
        val mongoConnection = UriMongoConnection(mongoUri)

        dataProviders.forEach {
            layerMap[it.key] = MongoStorageLayer(
                mongoConnection, "Scala", it.key, it.value.getType()
            )
        }
    }

}
