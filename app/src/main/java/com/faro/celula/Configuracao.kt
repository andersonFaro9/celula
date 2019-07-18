package com.faro.celula

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class Configuracao : Application() {
    override fun onCreate() {
        Realm.init(applicationContext)
         RealmConfiguration.Builder()
             .name("celula.realm").schemaVersion(0).migration(CelulaMigration()).build()

        super.onCreate()
    }
}