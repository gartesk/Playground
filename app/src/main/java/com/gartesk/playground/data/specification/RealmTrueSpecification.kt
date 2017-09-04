package com.gartesk.playground.data.specification

import com.gartesk.playground.data.entity.DataItem
import io.realm.RealmQuery

class RealmTrueSpecification() : RealmCompositeSpecification<DataItem>() {

    override fun apply(query: RealmQuery<DataItem>): RealmQuery<DataItem> = query
}