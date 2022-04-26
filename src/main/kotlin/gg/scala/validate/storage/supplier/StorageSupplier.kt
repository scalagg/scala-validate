package gg.scala.validate.storage.supplier

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
abstract class StorageSupplier<T>
{
    val layer = mutableMapOf<String, T>()

    abstract fun getType(): Class<T>
    abstract fun getId(): String
}
