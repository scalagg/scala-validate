package gg.scala.validate.plugins

import gg.scala.validate.storage.supplier.impl.MinecraftNetworkDataSupplier
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

/**
 * @author puugz
 * @since 03/10/2021 02:06
 */
fun Application.configureDataSupplier() {
    install(ContentNegotiation) {
        json()
    }

    MinecraftNetworkDataSupplier.init()
}