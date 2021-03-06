package com.reactnativemodule.protocol

import com.reactnativemodule.BuildConfig
import com.reactnativemodule.protocol.v2.BlueTraceV2
import java.util.*

//we're loosely using the characteristic IDs to indicate the protocol version?
//will this code become too bloated in time?
object BlueTrace {

    val characteristicToProtocolVersionMap = mapOf<UUID, Int>(
        UUID.fromString("117BDD58-57CE-4E7A-8E87-7CCCDDA2A804") to 2
    )

    val implementations = mapOf<Int, BlueTraceProtocol>(
        2 to BlueTraceV2()
    )

    fun supportsCharUUID(charUUID: UUID?): Boolean {
        if (charUUID == null) {
            return false
        }

        characteristicToProtocolVersionMap[charUUID]?.let { version ->
            return implementations[version] != null
        }
        return false
    }

    //gets the protocol implementation via charUUID map.
    //fallbacks to V2
    fun getImplementation(charUUID: UUID): BlueTraceProtocol {
        val protocolVersion = characteristicToProtocolVersionMap[charUUID] ?: 1
        return getImplementation(protocolVersion)
    }

    //gets the protocol implementation.
    //fallbacks to V2
    fun getImplementation(protocolVersion: Int): BlueTraceProtocol {
        val impl = implementations[protocolVersion]
        return impl ?: BlueTraceV2()
    }
}
