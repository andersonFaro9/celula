package com.faro.celula


import java.util.ArrayList

import io.realm.Realm

class RealmHelper(internal var realm: Realm?) {

    //WRITE
    fun save(spacecraft: Spacecraft) {
        realm?.executeTransaction { realm -> realm.copyToRealm(spacecraft) }

    }

    //READ
    fun retrieve(): ArrayList<String> {
        val spacecraftNames = ArrayList<String>()
        val spacecrafts = realm?.where(Spacecraft::class.java)?.findAll()

        if (spacecrafts != null) {
            for (s in spacecrafts) {
                spacecraftNames.add(s?.name.toString())
            }
        }

        return spacecraftNames
    }
}