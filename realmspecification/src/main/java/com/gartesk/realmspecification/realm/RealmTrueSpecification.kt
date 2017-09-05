package com.gartesk.realmspecification.realm

import io.realm.RealmObject
import io.realm.RealmQuery

class RealmTrueSpecification<T : RealmObject> : RealmCompositeSpecification<T>() {

    override fun apply(query: RealmQuery<T>): RealmQuery<T> = query
}