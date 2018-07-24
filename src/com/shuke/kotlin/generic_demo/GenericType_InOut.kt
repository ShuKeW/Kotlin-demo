package com.shuke.kotlin.generic_demo

/**
 * 泛型的变型相关
 *
 * */

fun main(args: Array<String>) {
    val typeClassA = TypeClass<TypeA>(TypeA())
    val typeClassB = TypeClass<TypeB>(TypeB())
    genericType_inout1(typeClassA)
    genericType_inout2(typeClassB)
    /**
     * 非法调用，虽然TypeB是TypeA的子类，但是typeClassB不能当做TypeClassA的子类型
     */
//    genericType_inout1(typeClassB)

    /**
     * 为了解决上边的问题，使用协变
     * 协变：TypeB是TypeA的子类型，那么TypeClassOut<TypeB>也是TypeClassOut<TypeA>的子类型
     */
    val typeClassOutA = TypeClassOut<TypeA>(TypeA())
    val typeClassOutB = TypeClassOut<TypeB>(TypeB())
    genericType_inout3(typeClassOutA)
    genericType_inout4(typeClassOutB)
    genericType_inout3(typeClassOutB)

    /**
     * 逆变：协变的镜像
     * 逆变：TypeB是TypeA的子类型，那么TypeClassOut<TypeA>是TypeClassOut<TypeB>的子类型
     */
    val typeClassInA = TypeClassIn<TypeA>(TypeA())
    val typeClassInB = TypeClassIn<TypeB>(TypeB())
    genericType_inout5(typeClassInA)
    genericType_inout6(typeClassInB)
    genericType_inout6(typeClassInA)

}

open class TypeA {
    open fun method() {
        println("this is TypeA's method")
    }
}

class TypeB : TypeA() {
    override fun method() {
        println("this is TypeB's method")
    }
}

class TypeClass<T : TypeA>(val type: T) {

}

/**
 * 协变类：TypeB是TypeA的子类型，那么TypeClassOut<TypeB>也是TypeClassOut<TypeA>的子类型
 * 注意：1、out还有一层含义是out位置，即返回值位置
 *      2、所以，在协变类外不能更改T类型的值
 *      3、构造方法的参数可以修改，但是也可以协变
 *          当参数为val的时候，在构造方法的时候只初始化一次，故不影响
 *          当参数为var的时候，添加private修饰符，保证类外无法访问，故也可协变
 */
class TypeClassOut<out T : TypeA>(private var type: T) {
    private var t: T? = null
    /**
     * 可以放在in位置？？？？
     */
    fun <T> changeType(newType: T): T {
        print("changeType:")
        (newType as? TypeA)?.method()
        return newType
    }

    /**
     * 这里虽然对外提供了方法，更改了out的值，但是更改还是本类内发生的，外部并不能提供一个新的T类型的值
     */
    fun changeType() {
        type.method()
        type = TypeA() as T
        type.method()
        t = TypeA() as T
    }
}

/**
 * 逆变类
 */
class TypeClassIn<in T : TypeA>(private var type: T) {
    fun invokeTMethod() {
        type.method()
    }

}

fun genericType_inout1(t: TypeClass<TypeA>) {
//    t.type.method()
}

fun genericType_inout2(t: TypeClass<TypeB>) {
//    t.type.method()
}

fun genericType_inout3(t: TypeClassOut<TypeA>) {
//    t.type.method()
//    t.changeType(TypeA())
}

fun genericType_inout4(t: TypeClassOut<TypeB>) {
//    t.type.method()
//    t.changeType(TypeB())
//    t.changeType()
}

fun genericType_inout5(t: TypeClassIn<TypeA>) {
    t.invokeTMethod()
}

fun genericType_inout6(t: TypeClassIn<TypeB>) {
    t.invokeTMethod()
}