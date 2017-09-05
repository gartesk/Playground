package com.gartesk.playground.data

import android.content.Context
import com.gartesk.playground.data.entity.RealmDataItem
import com.gartesk.playground.data.mapper.RealmDataItemSpecificationMapper
import com.gartesk.playground.data.mapper.RealmDataMapper
import com.gartesk.playground.domain.model.DataItem
import com.gartesk.realmspecification.Specification
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDataStorage(context: Context) : DataStorage {

    private val realmDataMapper: RealmDataMapper = RealmDataMapper()
    private val realmDataItemSpecificationMapper = RealmDataItemSpecificationMapper()

    init {
        Realm.init(context)
        val realmConfiguration = RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.deleteRealm(realmConfiguration)
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    override fun saveItem(dataItem: DataItem) {
        val realmDataItem = realmDataMapper.mapToRealm(dataItem)
        var realm: Realm? = null
        try {
            realm = Realm.getDefaultInstance()
            realm.executeTransaction { transactionRealm -> transactionRealm.copyToRealm(realmDataItem) }
        } finally {
            try {
                realm?.close()
            } catch (throwable: Throwable) {
            }
        }
    }

    override fun getItems(specification: Specification<DataItem>): List<DataItem> {
        var realm: Realm? = null
        try {
            realm = Realm.getDefaultInstance()
            val results: MutableList<DataItem> = mutableListOf()
            realm.executeTransaction { transactionRealm ->
                results.addAll(realmDataItemSpecificationMapper.map(specification)!!
                        .apply(transactionRealm.where(RealmDataItem::class.java)).findAll()
                        .map { realmDataItem -> realmDataMapper.mapFromRealm(realmDataItem) })
            }
            return results
        } finally {
            try {
                realm?.close()
            } catch (throwable: Throwable) {
            }
        }
    }
}