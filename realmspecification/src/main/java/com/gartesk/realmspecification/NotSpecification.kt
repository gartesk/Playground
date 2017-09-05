package com.gartesk.realmspecification

class NotSpecification<T>(val one: Specification<T>) : CompositeSpecification<T>() {

    override fun isSatisfiedBy(item: T): Boolean = !one.isSatisfiedBy(item)
}