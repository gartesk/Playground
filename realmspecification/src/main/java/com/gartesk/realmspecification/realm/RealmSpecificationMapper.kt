package com.gartesk.realmspecification.realm

import com.gartesk.realmspecification.*
import io.realm.RealmObject

open class RealmSpecificationMapper<T : RealmObject, S> {

    open fun map(specification: Specification<S>): RealmSpecification<T>? = when (specification) {
        is AndSpecification<S> -> RealmAndSpecification<T>(map(specification.one)!!, map(specification.other)!!)
        is OrSpecification<S> -> RealmOrSpecification<T>(map(specification.one)!!, map(specification.other)!!)
        is NotSpecification<S> -> RealmNotSpecification<T>(map(specification.one)!!)
        is TrueSpecification<S> -> RealmTrueSpecification<T>()
        else -> null
    }
}