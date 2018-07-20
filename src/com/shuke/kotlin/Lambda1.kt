package com.shuke.kotlin

import com.shuke.kotlin.bean.Persion

/**
 * Lambda表达式的基础知识
 * 定义：一个lambda把一小段代码进行编码，你能把它当做值到处传递
 * 声明：{参数->函数体}
 */
fun main(args: Array<String>) {
    /**
     * 使用一：可以把Lambda存储在一个变量中，把这个变量当做函数对待
     */
    val sun = { x: Int, y: Int -> x + y }
    println(sun(2, 3))

    /**
     * 使用二：作为函数的参数，如何定义参数在高阶函数中介绍
     */
    val persionList = listOf<Persion>(Persion("shuke", 28, "beijing"), Persion("beita", 30, "beijing"))
    var maxAgePersion = persionList.maxBy({ persion: Persion -> persion.age })


    /**
     * 使用三：调用时候的简化
     *        1、如果lambda是函数的唯一参数，可以省略函数的参数括号
     *        2、如果lambda是函数的最后一个参数，可以将lambda放到函数的参数括号外边
     */
    maxAgePersion = persionList.maxBy { persion: Persion -> persion.age }
    persionList.getOrElse(3) { index: Int -> println("index ${index} 没有找到合适的值") }

    /**
     * 使用四：lambda的参数的简化
     *        1、如果lambda的参数可以通过类型推到出来，可以不用显示指定
     *        2、默认参数it，当lambda只有一个参数且可以推到出来，会默认生成为it
     */
    maxAgePersion = persionList.maxBy { persion -> persion.age }
    maxAgePersion = persionList.maxBy { it.age }

    println("年龄最大的人：${maxAgePersion}")

    /**
     * 使用五
     */
    printListWithFix(listOf<String>("403 Forbidden", "404 Not Found", "500 Internal Server Error"), "Error", true)

    /**
     * 使用六
     */
    testCollectionLambdaApi(persionList)

    /**
     * 使用七
     */
    collentionToSequence(persionList)
}

/**
 * 使用五：lambda捕捉：在lambda中访问变量
 *        因为lambda和java的匿名内部类一样，最终是编译成了你们内部类。而java中匿名内部类访问外部的变量，需要为final修饰。
 *        但是Kotlin不用，且还能改变外部的值
 *        原理：1、val变量和java的final一样，捕捉的时候是复制了一份到lambda的作用于内
 *             2、当捕捉到var变量的时候，生成val的类Ref，将var变量存储在类Ref中，然后捕捉Ref
 */
private fun printListWithFix(list: List<String>, fix: String, b: Boolean) {
    var clientErrorCount = 0
    var serverErrorCount = 0
    list.forEach {
        //访问参数的值
        println("${fix} : ${it}")
        if (it.startsWith("4")) {
            //修改外部的值
            clientErrorCount++
        } else if (it.startsWith("5")) {
            serverErrorCount++
        }
    }
    println("${clientErrorCount}个clientError")
    println("${serverErrorCount}个serverError")
}

/**
 * 使用六：集合中常用的函数式api调用
 */
private fun testCollectionLambdaApi(list: List<Persion>) {
    /**
     * 1、filer：遍历集合，每一个元素作为lambda的参数，如果lambda为true，则把这个元素存入到新的集合中，返回这个新的集合
     */
    val filterList = list.filter { it.address == "beijing" }
    println("在北京的人：${filterList}")

    /**
     * 2、map：遍历集合，每一个元素作为lambda的参数，把这个lambda表达式的返回值存入到新的集合中，返回这个集合
     */
    val mapList = list.map { it.name }
    println("用name组成一个新的集合：${mapList}")

    /**
     * 还有
     *    all：是否所有元素都满足lambda
     *    any：是否有元素满足lambda
     *    count：满足lambda的元素数量
     *    find：找到满足lambda的元素
     *    groupBy：用lambda的值作为key，将集合转换成Map
     *    flatMap/flatten
     *  就不具体演示了，其实通过看api是更好的学习方式
     */
}

/**
 * 使用七：序列
 *        序列是一种惰性集合的操作方式。
 *        因为前边对集合的操作，例如filter、map等返回的是一个新集合。如果对一个size很大的集合要做多次操作，那么会创建很多的中间集合。
 *        所以是一种资源的损耗，所以引入序列。
 *        序列的原理是把多步操作合并成一步，在最后末端操作的时候统一触发。
 *      如何理解惰性：
 *        toList()是末端操作，只有调用了这个方法，才会执行操作
 *        这个操作过程呢并不是说先应用map生成一个name的集合，然后在这个集合的基础上在应用filter
 *        而是对把map和filter合为一个操作，依次应用到元素上。或者说依次依次遍历元素，先用map再用filter，然后下一个元素用map再filter
 */
private fun collentionToSequence(list: List<Persion>) {
    val sequenceList = list.asSequence().map { it.name }.filter { it.contains("e") }.toList()
    println("使用惰性集合：${sequenceList}")
}

