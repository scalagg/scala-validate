package gg.scala.validate.provide

/**
 * @author GrowlyX
 * @since 10/2/2021
 */
abstract class DataProvider<T> {

    abstract fun getType(): Class<T>

}
