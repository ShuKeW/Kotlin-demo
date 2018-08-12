package com.shuke.kotlin.class_demo

import kotlin.reflect.KProperty


/**
 * Kotlin中的类：属性
 *              定义：包含属性申明和getter/setter方法
 *              谨记：Kotlin要求属性必须在构造的时候初始化
 * */
fun main(args: Array<String>) {
    val testAttribute2 = TestAttribute2("attribute1")
    testAttribute2.attribute2 = "attribute"
    println("${testAttribute2.attribute2} ${testAttribute2.hasUpCase}")
}

/**
 * 使用一：默认属性
 *          无论是构造方法中申明的还是类中申明的，都会自动生成getter/setter方法
 *          必须有val/var修饰
 *          这里注意：我们在构造方法里边已经说过，如果主构造的参数没有val/var修饰，只是表示参数，而不表示属性
 */
class TestAttribute1(var attribute1: String) {
    var attribute2: String = ""
}

/**
 * 使用二：自定义getter、setter
 *        如果在setter和getter的时候，除了默认的实现，还需要额外的操作，就可以自定义getter和setter
 *        支持字段：用field访问
 *                 如果使用默认的访问器实现，编译器就会自动生成支持字段
 *                 支持字段：这里要区分支持字段和属性的关系
 *                          我们申明了一个属性，会默认生成和属性同名的支持字段，从表面看它们是同一个东西，但是属性是一个上层的用来访问的接口，而支持字段是用来实际存储值的地方
 *                 默认的getter和setter都是通过自动生成了支持字段来访问的
 *                 当我们自定义访问器的时候
 *                     当我们想要访问到支持字段的值的时候，可以借助field
 *                     如果并且没有使用field，支持字段将不会被呈现出来
 *                     例如下边的 testSupportField，我们虽然是申明了属性，但是编译完成后只有setTestSupportField和getTestSupportField两个方法
 * */

class TestAttribute2(var attribute1: String) {
    var attribute2: String? = null
        set(value) {
            field = if (!value.isNullOrBlank()) {
                value!!.toUpperCase()
            } else {
                value
            }
        }

    val hasUpCase: Boolean
        get() {
            attribute2?.forEach {
                if (it in 'A'..'Z') {
                } else {
                    return false
                }
            } ?: return false
            return true
        }

    var testSupportField
        get() = true
        set(value) {
            println("set testSupportField")
        }
}

/**
 * 使用三：lateinit var ->延迟初始化
 *        因为Kotlin要求属性必须在构造的时候被初始化，因此为了解决一些在构造的时候不需要初始化的属性，引入延迟初始化：lateinit var
 *        为什么 lateinit 和 var连用，是因为延迟初始化的属性是在构造方法之外初始化，而val会被编译成final的，所以 lateinit 必须是 var的
 *        下边的例子中，attribute是lateinit的，所以不需要在构造的时候初始化，可以在程序任何时候调用方法method来初始化
 * */
class TestAttribute3 {
    lateinit var attribute: String

    fun method() {
        attribute = "attribute"
    }
}

/**
 * 使用四：by ->属性委托
 *          场景：当某些属性的访问器比把值直接存储在支持字段中更复杂，且这个复杂的逻辑可以多处使用，我们就可以使用委托属性
 *          用法：下例中arg1，即把arg1的访问器逻辑委托给类Delegate的对象delegate
 *          约定：委托类必须具有getValue和setValue(用于可变属性)，这样arg1的getter和setter其实是调用delegate.getValue和delegate.setValue
 *          原理：首先，在定义委托类的时候，getValue和setValue的参数必须有委托属性的类、属性的反射类型
 *               然后，在委托属性的访问器中调用委托类对象的getValue、setValue
 *          Kotlin中的实现：Map、MutableMap已经实现了getValue和setValue，因此可以直接使用属性委托
 * */

class FiledBy {
    private val delegate = Delegate()
    var arg1: Int by delegate
}

class Delegate {
    operator fun getValue(filedBy: FiledBy, property: KProperty<*>): Int {
        println("调用getValue")
        return 1
    }

    operator fun setValue(filedBy: FiledBy, property: KProperty<*>, i: Int) {

    }
}

/**
 * 使用五：惰性加载 和 by lazy{}
 *        场景：第一次访问该属性的时候才会创建对象。用于初始化比较耗时且并不需要每次都初始化。
 *        支持属性：实现惰性初始化，需要用到支持属性，和支持字段的概念相似。
 *                 下面的例子中，_emails是emails的支持属性，emails提供对属性的读取访问，_emails用来存储这个值。
 *                 这样，当访问emails的时候，如果为空，就新创建，从而达到了惰性初始化的目的。
 *        by lazy{}:
 *                 Kotlin提供了标准库函数lazy来简化惰性加载
 *                 原理：
 *                     lazy函数返回一个对象，这个对象有一个getValue的方法，因此可以与by一起来创建一个委托属性
 *                     lazy的参数是一个lamdba，可以使用这个lamdba来初始化
 *                     lazy默认是线程安全的，也可设置
 * */
class Email {}

private fun loadEmails(persion: Persion): List<Email> {
    return listOf()
}

class Persion(val name: String) {
    private var _emails: List<Email>? = null

    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this)
            }
            return _emails!!
        }

    val emailList:List<Email> by lazy { loadEmails(this) }

}