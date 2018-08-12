package com.shuke.kotlin.class_demo

/**
 * Kotlin中的抽象类：
 *              语法：关键abstract
 *                  abstract class 类名{
 *                  }
 *              注：和java中的抽象类很相似
 *
 * */
fun main(args: Array<String>) {

}

/**
 * 使用一：定义
 *          抽象类可以包含抽象方法和非抽象方法
 *          也可以包含抽象属性和非抽象属性
 *              原理：抽象属性其实是编译成了抽象的getter和setter方法
 *                   因此，在抽象类中是可以直接使用抽象属性，只不过是调用抽象的抽象的getter和setter(类似于定义抽象的方法，子类实现，在抽象类中调用)
 *          抽象类不可被实例化，但是可以有构造方法(包括主构造和从构造)，来被子类实例化
 *          子类必须重写抽象的成员（继承在专门的地方整体讲解）
 *
 * */

abstract class Animal {
    val name: String
    abstract var weight: Float

    constructor(name: String) {
        this.name = name
        weight = 10.0f
    }

    abstract fun voice()

    fun flay() {
        println("I can flay")
    }

    open fun swim() {
        println("I can swim")
    }
}
