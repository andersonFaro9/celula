package com.faro.celula

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Nota : RealmObject() {

    @PrimaryKey
    var id: String? = null

    var nota: String? = null
    var detalhes: String? = null
}