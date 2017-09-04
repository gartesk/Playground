package com.gartesk.playground.data.specification.implementation

import com.gartesk.playground.data.entity.DataItem
import com.gartesk.playground.data.specification.RealmCompositeSpecification
import io.realm.RealmQuery

class RealmItemCountBetweenSpecification(private val countFrom: Int, private val countTo: Int)
    : RealmCompositeSpecification<DataItem>() {

    override fun apply(query: RealmQuery<DataItem>): RealmQuery<DataItem> =
            query.between("count", countFrom, countTo)
}
