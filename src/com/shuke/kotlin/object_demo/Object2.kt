package com.shuke.kotlin.object_demo

/**
 * @创建人 shukewei
 * @创建时间 2018/7/24 下午4:32
 * @类描述     companion object：伴生对象
 *              可以直接使用 类名. 来调用companion object的方法
 */
fun main(args: Array<String>) {
    /**
     * 使用一
     */
    println(A.count)
    A.function()

    /**
     * 使用二
     */
    val chinaPeople = People.newChinaPeople()
    val usaPeople = People.newUsaPeople()
    println("${chinaPeople.area}  ${usaPeople.area}")
}

/**
 * 使用一：companion object
 *        原理：
 *          co编译成A的public static final class的内部类
 *          在A中申明一个单例的co的实例INSTANCE
 *          将co的属性申明成A的 private static的，然后co的getter和setter调用A
 *        由此可见，伴生对象内部可以访问外部类的私有成员
 *        因此，这是Kotlin种实现类的静态变量和方法的好办法，因为Kotlin中没有static关键字
 *
 */
class A {
    companion object co {
        val count = 3
        var size = 5
        fun function() {
            println("************")
        }
    }
}

/**
 * 使用二：工厂方法模式：伴生对象实战
 *        思考：放在Activity启动上是不是很合适
 */
class People private constructor(val area: String) {
    companion object {
        fun newChinaPeople() = People("China")
        fun newUsaPeople() = People("Usa")
    }
}

/**
 * 使用三：伴生对象实现接口
 *        这里Animation实例可以当做AnimationFormat类型使用
 */
interface AnimationFormat {
    fun formatAnim()
}

class Animation(species: String) {
    companion object : AnimationFormat {
        override fun formatAnim() {

        }

    }
}
/**
 * 使用四：还可以为伴生对象申明扩展函数
 */
