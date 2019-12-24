package com.faro.celula

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class NotaBd : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var assunto: String? = null
    var texto: String? = null

}