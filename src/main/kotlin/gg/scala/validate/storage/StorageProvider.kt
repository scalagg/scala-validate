package gg.scala.validate.storage

import com.solexgames.datastore.commons.connection.impl.mongo.UriMongoConnection
import com.solexgames.datastore.commons.layer.impl.MongoStorageLayer
import gg.scala.validate.address
import gg.scala.validate.provider.DataProvider
import gg.scala.validate.provider.impl.MinecraftNetworkDataProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

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
        // lmao
        val mongoConnection = UriMongoConnection(
            "mongodb://$address:27017/admin"
        )

        dataProviders.forEach {
            layerMap[it.key] = MongoStorageLayer(
                mongoConnection, "Scala", it.key, it.value.getType()
            )
        }
    }
}
