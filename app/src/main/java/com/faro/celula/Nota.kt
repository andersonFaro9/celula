package com.faro.celula

import io.realm.RealmObject

open class Nota : RealmObject() {

    var nota: String? = null
    var detalhes: String? = null
}