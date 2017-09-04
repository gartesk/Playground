package com.gartesk.playground.data

import android.content.Context
import com.gartesk.playground.data.entity.DataItem
import com.gartesk.playground.data.specification.RealmSpecification
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDataStorage(context: Context) {

    private val realm: Realm

    init {
        Realm.init(context)
        val realmConfiguration = RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.deleteRealm(realmConfiguration)
        Realm.setDefaultConfiguration(realmConfiguration)
        realm = Realm.getDefaultInstance()
    }

    fun saveItem(dataItem: DataItem) {
        realm.executeTransaction { transactionRealm -> transactionRealm.copyToRealm(dataItem) }
    }

    fun getItems(specification: RealmSpecification<DataItem>): List<DataItem> =
            specification.apply(realm.where(DataItem::class.java)).findAll()

    fun close() {
        realm.close()
    }
}