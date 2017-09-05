package com.gartesk.playground.domain.command

import com.gartesk.playground.data.DataStorage
import com.gartesk.playground.domain.model.DataItem
import com.gartesk.realmspecification.Specification

class DataItemsCommand(val dataStorage: DataStorage) {

    fun saveDataItem(dataItem: DataItem) = dataStorage.saveItem(dataItem)

    fun getDataItems(specification: Specification<DataItem>) = dataStorage.getItems(specification)
}