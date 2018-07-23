package com.shuke.kotlin.lambda_demo

/**
 * @创建人 shukewei
 * @创建时间 2018/7/23 上午11:22
 * @类描述 lambda的接受者
 */

fun main(args: Array<String>) {
    /**
     * 使用一
     */
    val result = buildString {
        //这里this是接收者对象，对应声明的时候的接受者类型
        this.append("Hello ")
        append("world!")
    }
    println(result)
}

/**
 * 使用一：申明接收者
 *        1、语法：
 *              接收者类型.（参数列表）-> 返回类型
 *        2、这种类型叫 扩展函数类型。因为它的声明像函数类型和扩展函数的结合
 *        3、调用：
 *              使用扩展函数的方式来调用
 *        4、注意：
 *              虽然声明和调用都比较像扩展函数，但是它不是扩展函数
 *              就像本例中builderAction并不会编译成StringBuilder的方法，只是使用了和扩展函数一样的语法
 */
private fun buildString(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}
