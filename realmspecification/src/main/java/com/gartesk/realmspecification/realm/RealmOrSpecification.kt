package com.gartesk.realmspecification.realm

import io.realm.RealmModel
import io.realm.RealmQuery

class RealmOrSpecification<T : RealmModel>(private val one: RealmSpecification<T>,
                                           private val other: RealmSpecification<T>)
    : RealmCompositeSpecification<T>() {

    override fun apply(query: RealmQuery<T>): RealmQuery<T> =
            other.apply(one.apply(query.beginGroup()).or()).endGroup()
}