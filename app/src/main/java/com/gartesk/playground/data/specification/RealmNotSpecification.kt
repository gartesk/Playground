package com.gartesk.playground.data.specification

import io.realm.RealmModel
import io.realm.RealmQuery

class RealmNotSpecification<T : RealmModel>(private val one: RealmSpecification<T>)
    : RealmCompositeSpecification<T>() {

    override fun apply(query: RealmQuery<T>): RealmQuery<T> = one.apply(query.not())
}
