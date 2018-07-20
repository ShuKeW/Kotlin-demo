package com.shuke.kotlin

import java.util.*

/**
 * 星号投影
 */
fun main(args:Array<String>){
    normalStar()
}

/**
 * 星号投影的一般用法
 */
fun normalStar(){
    val listString = mutableListOf<String>("a","b","c")
    val listChar = mutableListOf<Char>('a','b','c')
    val listUnknow:MutableList<*> = if (Random().nextBoolean()) listString else listChar
    /**
     * 获取值可以正常调用
     */
    println(listUnknow.first())

    /**
     * 但是不能写入
     * 因为编译器不知道listUnknow的具体类型
     * 原理：listUnknow实际编译成了MutableList<out Any?>，故只能访问out位置的方法
     */
//    listUnknow.add("d")

}