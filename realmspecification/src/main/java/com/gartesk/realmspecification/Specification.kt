package com.gartesk.realmspecification

interface Specification<T> {

    fun isSatisfiedBy(item: T): Boolean
    fun and(other: Specification<T>): Specification<T>
    fun or(other: Specification<T>): Specification<T>
    operator fun not(): Specification<T>
}