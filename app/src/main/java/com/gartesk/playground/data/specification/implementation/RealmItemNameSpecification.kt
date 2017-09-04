package com.gartesk.playground.data.specification.implementation

import com.gartesk.playground.data.entity.DataItem
import com.gartesk.playground.data.specification.RealmCompositeSpecification
import io.realm.RealmQuery

class RealmItemNameSpecification(private val name: String) : RealmCompositeSpecification<DataItem>() {

    override fun apply(query: RealmQuery<DataItem>): RealmQuery<DataItem> =
            query.equalTo("name", name)
}
