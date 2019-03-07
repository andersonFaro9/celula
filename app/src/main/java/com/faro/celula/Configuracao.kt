package com.faro.celula

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class Configuracao : Application() {
    override fun onCreate() {
        Realm.init(applicationContext)
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
        super.onCreate()
    }
}