package com.faro.celula

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CelulaModel : RealmObject() {

    @PrimaryKey
    var id: String? = null

    var titulo: String? = null
    var detalhes: String? = null
}