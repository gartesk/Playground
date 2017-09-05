package com.gartesk.realmspecification.realm

import io.realm.RealmModel
import io.realm.RealmQuery

interface RealmSpecification<T : RealmModel> {

    fun apply(query: RealmQuery<T>): RealmQuery<T>
    fun and(other: RealmSpecification<T>): RealmSpecification<T>
    fun or(other: RealmSpecification<T>): RealmSpecification<T>
    operator fun not(): RealmSpecification<T>
}