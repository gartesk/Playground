package com.gartesk.playground

import android.app.Application
import com.gartesk.playground.data.DataStorage
import com.gartesk.playground.data.RealmDataStorage
import com.gartesk.playground.domain.command.DataItemsCommand

class PlaygroundApplication : Application() {

    private lateinit var dataStorage: DataStorage

    override fun onCreate() {
        super.onCreate()
        dataStorage = RealmDataStorage(this)
    }

    fun createDataItemsCommand() = DataItemsCommand(dataStorage)
}