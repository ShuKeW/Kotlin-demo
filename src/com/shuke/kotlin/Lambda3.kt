package com.shuke.kotlin

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

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

    var result = filterString1("a1b2c3d4") {
        it in 'a'..'z'
    }
    println(result)

    /**
     * 使用三
     */
    result = filterString2("a1b2c3d4")
    println(result)

    /**
     * 使用四
     */
    val testInlineClass = TestInlineClass()
    testInlineClass.foo1(ReentrantLock())
    testInlineClass.foo2(ReentrantLock()) { println("foo2 action") }
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
fun filterString1(str: String, predicate: (c: Char) -> Boolean): String {
    val sb = StringBuilder()
    str.forEach {
        if (predicate(it)) {
            sb.append(it)
        }
    }
    return sb.toString()
}

/**
 * 使用三：函数类型的默认值和可空性
 */
fun filterString2(str: String, predicate: ((c: Char) -> Boolean)? = { it in '1'..'9' }): String {
    val sb = StringBuilder()
    str.forEach {
        if (predicate != null) {
            if (predicate(it)) {
                sb.append(it)
            }
        }
//        if(predicate?.invoke(it) == true){
//            sb.append(it)
//        }
    }
    return sb.toString()
}

/**
 * 使用四：inline：内联函数
 *        1、原理：
 *              虚拟机编译成字节码的时候，将内联函数包括内联函数的函数类型（lambda）的参数代码复制到调用处
 *              如foo1方法的字节码：
 *                  fun _foo1_(lock:Lock){
 *                      println("before sync")
 *                      lock.lock()
 *                      try{
 *                          println("action")
 *                      }finally{
 *                          lock.unlock()
 *                      }
 *                      println("after sync")
 *                  }
 *              因此，解决了lambda创建匿名内部类的效率问题
 *        2、限制
 *              foo2方法因为传递的是函数类型的变量，故不能内联
 *              foo2的字节码：
 *                  fun _foo2_(lock:Lock){
 *                      println("before sync")
 *                      lock.lock()
 *                      try{
 *                          body()
 *                      }finally{
 *                          lock.unlock()
 *                      }
 *                      println("after sync")
 *                  }
 *              因此，inline方法会内联，但是inline方法的参数不会内联
 *        3、总结：
 *              所以，当在调用inline方法的时候，只有显示给出了函数类型形参的lambda，而不是传递一个变量，才会把lambda也内联
 *        4、noinline
 *              将参数的lambda申明为非内联
 *        5、内联和集合、序列
 *              之前学了把集合的操作替换成序列，可以提高效率
 *              但是，集合的操作是内联的，序列的操作是非内联
 *              所以，使用集合会创建中间集合，使用序列会创建匿名内部类
 *              故，在使用小的集合的集合的时候还是选择使用集合为好
 *        6、何时使用内联
 *              非内联：虚拟机在将字节码转换成机器码的时候，也会只能的内联
 *              因此，当我们的参数有lambda的时候，为了提高性能，可以申明成内联
 *              但是，如果内联方法体过长，应该把与lambda无关的代码抽取到独立的非内联函数中
 */
private class TestInlineClass {
    /**
     * 使用inline自定义的synchronized
     */
    inline fun <T> synchronized(lock: Lock, action: () -> T): T {
        lock.lock()
        try {
            return action()
        } finally {
            lock.unlock()
        }
    }

    fun foo1(lock: Lock) {
        println("before sync")
        synchronized(lock) {
            println("action")
        }
        println("after sync")
    }

    /**
     * 调用内联函数的时候传递函数类型的变量而不是传递一个lambda实参
     */
    fun <T> foo2(lock: Lock, body: () -> T) {
        println("before sync")
        synchronized(lock, body)
        println("after sync")
    }

    /**
     * action2不会被内联
     */
    inline fun <T> synchronized(lock: Lock, action1: () -> T, noinline action2: () -> T) {

    }
}

/**
 * 使用五：控制流
 * */