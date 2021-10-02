package gg.scala.validate.storage

import com.google.gson.Gson
import com.solexgames.datastore.commons.connection.impl.mongo.UriMongoConnection
import com.solexgames.datastore.commons.layer.impl.MongoStorageLayer
import gg.scala.validate.KtorValidateArgs
import gg.scala.validate.ValidateConstants
import gg.scala.validate.model.MinecraftNetworkData
import gg.scala.validate.provide.DataProvider
import gg.scala.validate.provide.impl.MinecraftNetworkDataProvider
import gg.scala.validate.storage.supply.StorageSupplier

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
object StorageProvider {

    val dataProviders = mutableMapOf<String, DataProvider<*>>()
    val storageSuppliers = mutableMapOf<String, StorageSupplier<*>>()

    val layerMap = mutableMapOf<String, MongoStorageLayer<*>>()

    fun initialLoad() {
        dataProviders["minecraft"] = MinecraftNetworkDataProvider

        loadMongoResources()
    }

    private fun loadMongoResources() {
        val mongoConnection = UriMongoConnection(
            ValidateConstants.args.mongoUri
        )

        dataProviders.forEach {
            layerMap[it.key] = MongoStorageLayer(
                mongoConnection, "Scala", it.key, it.value.getType()
            )
        }
    }

}
