package com.gartesk.playground.data.specification

import com.gartesk.playground.data.entity.RealmDataItem
import com.gartesk.realmspecification.realm.RealmCompositeSpecification
import io.realm.RealmQuery

class RealmDataItemNameSpecification(private val name: String) : RealmCompositeSpecification<RealmDataItem>() {

    override fun apply(query: RealmQuery<RealmDataItem>): RealmQuery<RealmDataItem> =
            query.equalTo("name", name)
}