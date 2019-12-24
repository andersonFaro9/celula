package com.faro.celula

import android.app.Application
import com.onesignal.OneSignal
import io.realm.Realm
import io.realm.RealmConfiguration
import androidx.core.content.ContextCompat.getSystemService

class Configuracao : Application() {

    override fun onCreate() {

        Realm.init(applicationContext)
        val config = RealmConfiguration.Builder()

            .schemaVersion(3)
            .migration(CelulaMigration())
            .build()
        Realm.setDefaultConfiguration(config)


        super.onCreate()


        enviaNotificacaoOneSignal()
    }

    fun enviaNotificacaoOneSignal() {
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
    }


    override fun onTerminate() {
        Realm.getDefaultInstance().close()
        super.onTerminate()
    }
}