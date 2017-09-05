package com.gartesk.playground.domain.specification

import com.gartesk.playground.domain.model.DataItem
import com.gartesk.realmspecification.CompositeSpecification

class DataItemNameSpecification(val name: String): CompositeSpecification<DataItem>() {

    override fun isSatisfiedBy(item: DataItem): Boolean = item.name.equals(name)
}