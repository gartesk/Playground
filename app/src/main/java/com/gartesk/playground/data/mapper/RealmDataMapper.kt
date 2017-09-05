package com.gartesk.playground.data.mapper

import com.gartesk.playground.data.entity.RealmDataItem
import com.gartesk.playground.domain.model.DataItem

class RealmDataMapper {

    fun mapToRealm(dataItem: DataItem): RealmDataItem = RealmDataItem(dataItem.name, dataItem.count)

    fun mapFromRealm(realmDataItem: RealmDataItem): DataItem = DataItem(realmDataItem.name, realmDataItem.count)
}