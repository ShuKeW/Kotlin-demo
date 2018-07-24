package com.shuke.kotlin.generic_demo

/**
 * 泛型相关
 *
 */

fun main(args: Array<String>) {

    val listString = listOf("lisaA", "lisaB", "lisaC")
    val listInt = listOf(1, 2, 3)
    val setString = setOf<String>("setA", "setB", "setC")

    /**
     * 注意在调用泛型方法的时候，必须要在方法名后边指定泛型类型实参
     * 但是，如果函数参数使用了泛型形参（T）,那么函数参数实参指定了泛型类型实参，可以省略方法名后边的泛型类型实参(默认是一致，但是以方法名后边优先)
     */
    genericMethod<String>()

    genericType1(listString)

    genericType2(listString)
    genericType2(listInt)
    genericType2(setString)

    genericType3("abc", listInt)
}

/**
 * 泛型类：在类名后边添加 <>
 * 那么在 类的内部（主构造也是内部哦）就可以使用 T
 * 上界约束：默认T:Any?，因此要想T非空，可以显示T:Any
 */

class genericClass<T> {

}

/**
 * 泛型类：在类名边加 <>
 * 那么在这个 方法体 和 参数列表里 都可以使用 T
 */

fun <T> genericMethod() {
    println("泛型函数")
}

/**
 * 泛型擦除：和java一样
 */
fun <T> genericType1(list: List<T>) {
    println("传入的参数list：${list::class}")
    /**
     * 编译不通过，虽然传入的参数是List<String类型>，但是被擦除了，所以编译器为了防止不必要的错误发生，制止这种行为
     */
//    if(list is List<String>){
//
//    }
}

/**
 * *：投影运算符
 * 忽略 泛型类型参数，进而用来判断
 */
fun <T> genericType2(value: Collection<T>) {
    if (value is List<*>) {
        println("value is List<*>")
    } else if (value is Set<*>) {
        println("value is Set<*>")
    }
}

/**
 * inline+reified：实化类型参数，是泛型擦除的解决办法
 * 即在方法体内 T 可以当做实际的类型来用
 * 因为：内联之后，会把方法体拷贝到调用处，调用的时候已经指定了泛型函数的泛型类型，所以在这个方法体内，T的类型已知，且不会擦除
 */
inline fun <reified T> genericType3(value: T, list: List<T>) {
    println("T的类型T::class：${T::class}")
    println("T的类型T::class.java：${T::class.java}")
    println("value is T ${value is T}")
    list.forEach { println("list element is T:${it is T}") }
}