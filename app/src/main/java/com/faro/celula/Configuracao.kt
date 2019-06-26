package com.faro.celula

import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_add.*


class Configuracao : Application() {
    override fun onCreate() {
        Realm.init(applicationContext)
         RealmConfiguration.Builder()
             .name("celula.realm").schemaVersion(0).migration(MyMigration()).build()

        super.onCreate()
    }
}