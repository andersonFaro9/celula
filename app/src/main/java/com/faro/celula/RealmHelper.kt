package com.faro.celula


import java.util.ArrayList

import io.realm.Realm

class RealmHelper(internal var realm: Realm?) {

    //WRITE
    fun save(spacecraft: Spacecraft) {
        realm?.executeTransaction { realm -> realm.copyToRealm(spacecraft) }

    }


    fun retrieve():ArrayList<TesteModel> {
        val spacecraftNames = ArrayList<TesteModel>()
        val spacecrafts = realm?.where(Spacecraft::class.java)?.findAll()

        if (spacecrafts != null) {
            for (s in spacecrafts) {
                spacecraftNames.add(TesteModel(s?.nota.toString(),s?.detalhes.toString()))
            }
        }

        return spacecraftNames
    }
//    fun retrieve():  List<TesteModel> =  realm?.where(Spacecraft::class.java)?.findAll()?.map {
//
//        TesteModel(it?.nota.toString(), it?.detalhes.toString())
//    }.orEmpty()


}