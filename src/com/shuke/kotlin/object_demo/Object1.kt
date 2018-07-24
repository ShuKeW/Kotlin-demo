package com.shuke.kotlin.object_demo

import com.shuke.kotlin.bean.Persion

/**
 * @创建人 shukewei
 * @创建时间 2018/7/24 下午3:34
 * @类描述     object关键字：对象申明
 */

fun main(args: Array<String>) {
    /**
     * 使用一
     */
    Payroll.allEmployees.add(Persion("shuke", 28, "beijing"))
    Payroll.calculateSalary()
    /**
     * 使用二
     */
    val result = User.NameComparator.compare(User(1, "shuke"), User(2, "shuke"))
    println(result)
}

/**
 * 使用一：对象声明 -> 单例
 *        原理：
 *          object编译的时候
 *          生成一个public static final Payroll INSTANCE的单例
 *          然后将属性生成private static [final]的
 *          生成静态块来初始化单例和属性
 *          由此可见，对象申明和类是一致的，只不过对象申明不允许有构造方法（包括主、从构造）
 */
object Payroll {
    var count: Int = 2
    val allEmployees = arrayListOf<Persion>()

    fun calculateSalary() {
        for (persion in allEmployees) {
            //...
        }
    }
}

/**
 * 使用二：在类中申明对象申明
 *        这里虽然是在类User中申明了对象申明，但是并不是每一个User对象中都有一个对象申明的实例，而是只有一个
 *        原理：
 *          NameComparator编译成User的 public static final class 的内部类
 */
data class User(val id: Long, val name: String) {
    object NameComparator : Comparator<User> {
        override fun compare(o1: User, o2: User): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}