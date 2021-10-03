package gg.scala.validate.storage.supplier

import com.solexgames.datastore.commons.connection.impl.mongo.UriMongoConnection
import com.solexgames.datastore.commons.layer.impl.MongoStorageLayer
import gg.scala.validate.mongoUri

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
abstract class StorageSupplier<T> {

    protected lateinit var layer: MongoStorageLayer<T>

    abstract fun getType(): Class<T>
    abstract fun getId(): String

    abstract fun lateInit()

    fun init() {
        val mongoConnection = UriMongoConnection(mongoUri)

        this.layer = MongoStorageLayer(
            mongoConnection, "Scala",
            "minecraft", getType()
        )

        lateInit()
    }

}
