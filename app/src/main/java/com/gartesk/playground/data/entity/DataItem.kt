package com.gartesk.playground.data.entity

import io.realm.RealmObject

open class DataItem(var name: String = "", var count: Int = 0) : RealmObject()