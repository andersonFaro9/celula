package com.faro.celula


import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.FieldAttribute.*
import io.realm.RealmMigration
import io.realm.RealmSchema
import io.realm.RealmObjectSchema

class CelulaMigration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

        val schema = realm.schema

        if (oldVersion == 0L) {
            schema.get("NotaBd")
                ?.addField("id", String::class.java, PRIMARY_KEY)
                ?.addField("assunto", String::class.java)
                ?.addField("texto", String::class.java)

        }

        // ?.removeField("texto") remove o campo que não está mais no banco

        if (oldVersion == 1L) {
            schema.get("NotaBd")

                ?.addField("teste", String::class.java) //adiciona um novo campo
                ?.removeField("texto") // remove o campo que não existe mais

        }

        if (oldVersion == 2L) {
            schema.get("NotaBd")
                ?.addField("id", String::class.java, PRIMARY_KEY)
                ?.addField("assunto", String::class.java)
                ?.addField("texto", String::class.java)
        }


    }
}