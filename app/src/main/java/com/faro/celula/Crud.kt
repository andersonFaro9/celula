package com.faro.celula

import io.realm.Realm

class Crud(internal var realm: Realm?) {

    fun recupera(): ArrayList<Model> {

        val model = ArrayList<Model>()

        val dados = realm?.where(Nota::class.java)?.findAll()

        if (dados != null) {
            for (d in dados) {
                model.add(Model(d?.nota.toString(), d?.detalhes.toString()))
            }
        }

        return model
    }


    fun salva(nota: Nota) {
        realm?.executeTransaction { realm -> realm.copyToRealm(nota) }
    }
}