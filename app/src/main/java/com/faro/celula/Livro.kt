package com.faro.celula

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Livro  : RealmObject() {

    @PrimaryKey
    var id: Int? = null
    var nota: String? = null
    var detalhes: String? = null
}

