package com.reactnativemodule.streetpass.view

import com.reactnativemodule.streetpass.persistence.StreetPassRecord

class StreetPassRecordViewModel(record: StreetPassRecord, val number: Int = 1) {
    val version = record.v
    val modelC = record.modelC
    val modelP = record.modelP
    val msg = record.msg
    val timeStamp = record.timestamp
    val rssi = record.rssi
    val transmissionPower = record.txPower
    val org = record.org

    constructor(record: StreetPassRecord) : this(record, 1)
}
