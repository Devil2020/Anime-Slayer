package com.morse.common.local.db.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmObject(
    @PrimaryKey
    open var id: Int? = 0,
) : RealmObject() {
}


open class RealmTestObject(
    @PrimaryKey
    open var id: Int? = 0,
    open var name: String? = "Morse",
) : RealmObject() {
}