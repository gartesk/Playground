package com.gartesk.realmspecification.realm

import io.realm.RealmModel

abstract class RealmCompositeSpecification<T : RealmModel> : RealmSpecification<T> {

    override fun and(other: RealmSpecification<T>): RealmSpecification<T> =
            RealmAndSpecification(this, other)

    override fun or(other: RealmSpecification<T>): RealmSpecification<T> =
            RealmOrSpecification(this, other)

    override fun not(): RealmSpecification<T> = RealmNotSpecification(this)
}