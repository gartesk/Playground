package com.gartesk.playground.data.specification

import io.realm.RealmModel
import io.realm.RealmQuery

abstract class RealmCompositeSpecification<T : RealmModel> : RealmSpecification<T> {

    abstract override fun apply(query: RealmQuery<T>): RealmQuery<T>

    override fun and(other: RealmSpecification<T>): RealmSpecification<T> =
            RealmAndSpecification(this, other)

    override fun or(other: RealmSpecification<T>): RealmSpecification<T> =
            RealmOrSpecification(this, other)

    override fun not(): RealmSpecification<T> = RealmNotSpecification(this)
}
