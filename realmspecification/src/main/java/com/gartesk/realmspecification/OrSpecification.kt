package com.gartesk.realmspecification

class OrSpecification<T>(val one: Specification<T>, val other: Specification<T>) : CompositeSpecification<T>() {

    override fun isSatisfiedBy(item: T): Boolean = one.isSatisfiedBy(item) || other.isSatisfiedBy(item)
}