package com.gartesk.playground.data

import com.gartesk.playground.domain.model.DataItem
import com.gartesk.realmspecification.Specification

interface DataStorage {

    fun saveItem(dataItem: DataItem)
    fun getItems(specification: Specification<DataItem>): List<DataItem>
}