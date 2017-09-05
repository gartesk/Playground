package com.gartesk.playground.data.mapper

import com.gartesk.playground.data.entity.RealmDataItem
import com.gartesk.playground.data.specification.RealmDataItemCountBetweenSpecification
import com.gartesk.playground.data.specification.RealmDataItemNameSpecification
import com.gartesk.playground.domain.model.DataItem
import com.gartesk.playground.domain.specification.DataItemCountBetweenSpecification
import com.gartesk.playground.domain.specification.DataItemNameSpecification
import com.gartesk.realmspecification.Specification
import com.gartesk.realmspecification.realm.RealmSpecification
import com.gartesk.realmspecification.realm.RealmSpecificationMapper

class RealmDataItemSpecificationMapper : RealmSpecificationMapper<RealmDataItem, DataItem>() {

    override fun map(specification: Specification<DataItem>): RealmSpecification<RealmDataItem>? =
            when (specification) {
                is DataItemCountBetweenSpecification ->
                    RealmDataItemCountBetweenSpecification(specification.countFrom, specification.countTo)
                is DataItemNameSpecification -> RealmDataItemNameSpecification(specification.name)
                else -> super.map(specification)
            }
}