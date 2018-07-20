package com.shuke.kotlin

import javafx.beans.property.ReadOnlyProperty
import kotlin.reflect.KProperty

class FiledBy {
    private val delegate = Delegate();
    val arg1: Int by delegate
}

class Delegate {
    operator fun getValue(filedBy: FiledBy, property: KProperty<*>): Int {
        println("调用getValue")
        return 1
    }
}