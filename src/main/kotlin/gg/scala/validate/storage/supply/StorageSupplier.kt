package gg.scala.validate.storage.supply

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
abstract class StorageSupplier<T> {

    abstract fun fromJson(json: String): T

    fun init() {
        // TODO: 10/2/2021 setup mongo layer & stuff 
    }

}
