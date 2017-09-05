package com.gartesk.playground.data.specification

import com.gartesk.playground.data.entity.RealmDataItem
import com.gartesk.realmspecification.realm.RealmCompositeSpecification
import io.realm.RealmQuery

class RealmDataItemCountBetweenSpecification(private val countFrom: Int, private val countTo: Int)
    : RealmCompositeSpecification<RealmDataItem>() {

    override fun apply(query: RealmQuery<RealmDataItem>): RealmQuery<RealmDataItem> =
            query.between("count", countFrom, countTo)
}