package com.shuke.kotlin.function_demo

/**
 * @创建人 shukewei
 * @创建时间 2018/7/24 上午10:08
 * @类描述     函数：基础
 *            语法：
 *              1、代码块体
 *                  fun 函数名(参数列表):返回值{
 *                      return 返回值
 *                  }
 *              2、表达式体
 *                  fun 函数名(参数列表):返回值 = 表达式
 *              注意：返回值类型在可以类型推导出来的时候可以省略
 */
fun main(args: Array<String>) {
    /**
     * 使用一：命名参数
     *        在函数调用的时候指定参数的名字
     *        解决了函数调用的时候不知道各个实参的含义
     *        注意：当一个参数指定了名字，那后边的也都要指定
     */
    joinToString(collection = listOf("a"), separator = ",", prefix = "start", postfix = "end")
    joinToString(listOf("a"), ",", prefix = "", postfix = "")

    /**
     * 使用二：默认参数
     *        申明：指定参数的默认值
     *        调用：不给实参，会使用默认值
     *        规则：只能从后往前省略实参,当指定了命名参数，命名参数之前的默认参数可以省略
     *
     */
    joinToStringDefault(listOf("a"))
    joinToStringDefault(listOf("a"), prefix = "")

    /**
     * 使用三：可变参数
     *        注意：当实参是数组的时候，需要用 *(展开运算符) 来展开才可以传递
     */
    var list = myListOf("a", "b", "c")
    val array = arrayOf("c", "d", "e")
    list = myListOf<String>("haha", *array)
    println(list)

}

/**
 * 使用一：命名参数
 */
private fun <E> joinToString(collection: Collection<E>, separator: String, prefix: String, postfix: String) {
    println("separator：${separator},prefix：${prefix},postfix：${prefix}")
}

/**
 * 使用二：默认参数
 */
private fun <E> joinToStringDefault(collection: Collection<E>, separator: String = ",", prefix: String = "", postfix: String = "") {
    println("separator：${separator},prefix：${prefix},postfix：${prefix}")
}

/**
 * 使用三：可变参数
 *        使用vararg申明
 */
private fun <E> myListOf(vararg elements: E): List<E> {
    val list = MutableList<E>(elements.size) {
        elements[it]
    }
    return list
}