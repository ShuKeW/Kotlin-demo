package com.shuke.kotlin.function_demo

/**
 * @创建人 shukewei
 * @创建时间 2018/7/24 下午2:35
 * @类描述     函数：扩展函数和属性 -> 给别人的类添加方法
 */
fun main(args: Array<String>) {
    /**
     * 使用一
     */
    val lastChar = "Kotlin".lastChar
    println(lastChar)

    /**
     * 使用二
     */
    println("kotlin lastChar:${"kotlin".lastChar()}")

}

/*
* 使用一：扩展属性
*        语法：val/var 接收者类型.属性名:返回类型
*                       get()=
*                       set()=
*           接受者对象即被扩展类型，在get，set的方法体中可以调用接收者的非private的方法和属性，可用this引用，也可省略this
*        注意：必须通过getter、setter（var）来访问，因为扩展属性没有任何状态（即不能存储值）
* */
val String.lastChar: Char
    get() = get(length - 1)

/**
 * 使用二：扩展方法
 *        语法：fun 接收着类型.函数名():返回值{
 *              //方法体，注意在方法体内可以访问接收者的非private的方法和属性，可用this引用，也可省略this
 *        }
 */
fun String.lastChar(): Char {
    return this.get(length - 1)
}

/**
 * 使用三：在java中调用
 *        char l1 = Function2Kt.getLastChar("Kotlin")
 *        char l2 = Function2Kt.lastChar("Kotlin")
 *        原理：
 *          扩展方法和属性，都是生成了以扩展类型为第一个参数的普通函数
 *          也因此，扩展函数不能重载
 */
