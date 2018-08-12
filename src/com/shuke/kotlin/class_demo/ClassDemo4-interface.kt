package com.shuke.kotlin.class_demo

/**
 * Kotlin中的接口：
 *              语法：关键字interface
 *                  interface 接口名{
 *
 *                  }
 *              和java8中的接口非常类似
 *
 * */
fun main(args: Array<String>) {

}

/**
 * 使用一：定义
 *          接口中既可以包含抽象的成员也可以包含非抽象的成员
 *          抽象属性和方法默认为abstract
 *          注意：接口的属性比较特殊
 *               虽然也可以定义非抽象的属性，但是没有支持字段，即也没有filed来访问支持字段
 *               所以说，接口的属性只不过是隐式定义了setter和getter方法
 *               只有子类继承了才有支持字段
 * */
interface Clickable {
    var canClick: Boolean

    var isClick: Boolean
        get() {
            println("isClick get")
            return isClick
        }
        set(value) {
            println("isClick set")
            isClick = value
        }


    fun onClick()

    fun showOff() {
        println("Clickable showOff")
        canClick = true
    }
}
