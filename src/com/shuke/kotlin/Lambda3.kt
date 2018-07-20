package com.shuke.kotlin

/**
 * 高阶函数
 *      定义：高阶函数就是以另一个函数为参数或者返回值的函数
 */
fun main(args: Array<String>) {
    /**
     * 使用一
     */
    functionType()

    /**
     * 使用二
     */

    val result = filterString("a1b2c3d4") {
        it in 'a'..'z'
    }
    println(result)
}

/**
 * 使用一：函数类型
 *        即函数也是种类型
 *        变量名:后边就是变量类型，说明(x:Int,y:Int)->Int是一个类型，其实就是个函数类型即（参数）-> 返回值
 */
private fun functionType() {
    val sum: (x: Int, y: Int) -> Int = { x: Int, y: Int ->
        x + y
    }
    println(sum(2, 3))
}

/**
 * 使用二：在形参申明函数类型，并使用
 */
fun filterString(str: String, predicate: (c: Char) -> Boolean): String {
    val sb = StringBuilder()
    str.forEach {
        if (predicate(it)) {
            sb.append(it)
        }
    }
    return sb.toString()
}