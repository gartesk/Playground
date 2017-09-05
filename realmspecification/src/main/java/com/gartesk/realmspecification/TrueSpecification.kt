package com.gartesk.realmspecification

class TrueSpecification<T> : CompositeSpecification<T>() {

    override fun isSatisfiedBy(item: T): Boolean = true
}