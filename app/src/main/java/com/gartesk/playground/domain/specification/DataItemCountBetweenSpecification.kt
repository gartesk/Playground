package com.gartesk.playground.domain.specification

import com.gartesk.playground.domain.model.DataItem
import com.gartesk.realmspecification.CompositeSpecification

class DataItemCountBetweenSpecification(val countFrom: Int, val countTo: Int)
    : CompositeSpecification<DataItem>() {

    override fun isSatisfiedBy(item: DataItem): Boolean
            = item.count >= countFrom && item.count <= countTo
}