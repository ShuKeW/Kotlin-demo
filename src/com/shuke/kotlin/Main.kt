package com.shuke.kotlin

import com.shuke.kotlin.bean.Persion


fun main(args: Array<String>) {
//    println("Hello World")
    /**
     * 安全调用
     */
    var persion: Persion? = null
    println("persion == null ：${persion?.address}")
    persion = Persion("shuke", 28, null)
    println("age == null ：${persion?.address}")

    /**
     * 下标约定
     */
    val list = listOf<String>("a", "b", "c", "d", "e");
    println("list中的第3个元素：${list[2]}")

    /**
     * 委托属性
     */
    val filedBy = FiledBy()
    println(filedBy.arg1)


}


fun testReturn1(arg1: Int, arg2: Int): Int {
    return arg1 + arg2
}

fun testReturn2(arg1: Int, arg2: Int) = arg1 + arg2
fun testUnit(arg1: Int, arg2: Int): Unit {
    arg1 + arg2
    return
}