package com.test.project

import io.realm.RealmObject

open class Cat: RealmObject() {
    lateinit var text: String
    var favorite: Boolean = false
}