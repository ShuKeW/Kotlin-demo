package com.shuke.kotlin.reflection_demo

import kotlin.reflect.KClass

/**
 * 反射
 */
fun main(args: Array<String>) {
    val kclass: KClass<ReflectClass> = ReflectClass::class

    kclass.constructors.forEach {
        println(it)
    }
    println("-----------------------------------------")

    kclass.members.forEach {
        println("${it.name} : ${it}")
//        when (it.name) {
//            "add" -> {
//                val arg1 = 100
//                val arg2 = 200
//                println("${arg1} add ${arg2} = ${it.call(arg1, arg2,1)}")
//            }
//            "compare" -> {
//                val arg1 = 100
//                val arg2 = 200
//                println("${arg1} compare ${arg2} = ${it.call(arg1, arg2)}")
//            }
//        }
    }
    println("-----------------------------------------")

    val reflectClass = ReflectClass("shuke", 200)
    val add = ReflectClass::add
    println("add:${add.invoke(reflectClass, 100, 200)}")
    println("-----------------------------------------")

    kclass.members.forEach {
        when (it.name) {
            "add" -> {
                val arg1 = 100
                val arg2 = 200
                println("${arg1} add ${arg2} = ${it.call(reflectClass,arg1, arg2)}")
            }
            "compare" -> {
                val arg1 = 100
                val arg2 = 200
                println("${arg1} compare ${arg2} = ${it.call(reflectClass,arg1, arg2)}")
            }
        }
    }
    println("-----------------------------------------")
    val plus = ::plus
    plus.call(2, 3)
}

class ReflectClass(val arg1: String, val arg2: Int) {

    fun add(arg1: Int, arg2: Int) = arg1 + arg2

    fun <T : Int> compare(arg1: T, arg2: T): Int =
            when {
                (arg1 - arg2 > 0) -> 1
                (arg1 - arg2 == 0) -> 0
                (arg1 - arg2 < 0) -> -1
                else -> throw Exception("no result")
            }


}

fun plus(arg1: Int, arg2: Int) {
    println("${arg1}+${arg2} = ${arg1 + arg2}")
}