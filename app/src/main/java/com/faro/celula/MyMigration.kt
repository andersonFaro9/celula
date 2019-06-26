package com.faro.celula


import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration
import io.realm.RealmSchema

class MyMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        var oldVersion = oldVersion

        val schema = realm.schema

        if (oldVersion == 0L) {
            schema.create("Nota")
                .addField("id", String::class.java, FieldAttribute.PRIMARY_KEY)
                .addField("nota", String::class.java)
                .addField("detalhes", String::class.java)
            oldVersion++
        }


    }
}