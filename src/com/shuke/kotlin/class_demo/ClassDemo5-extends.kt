package com.shuke.kotlin.class_demo

/**
 * Kotlin中的类：继承和实现
 *              1、Kotlin中使用冒号(:)来代替java中的extends和implements关键字来实现继承和实现
 *              2、同样，是单继承多实现
 *              3、使用override修饰符来标注重写的属性和方法（override为强制的，如果不指定，就算这个方法和父类同名，也是新方法）
 *              4、子类重写父类的方法，也是使用super关键字，不过super的父类类型是通过泛型的方式指定
 *              5、继承类需要调用父类的构造，所以区分是继承还是实现可以看有没调用父类的构造
 *                  如果是主构造，需要在继承的时候以主构造的语法来实现（例如使用一中的继承Animal）
 *                  如果是从构造，使用从构造的语法即可（例如使用三种的继承View）
 * */
fun main(args: Array<String>) {

}

/**
 * 使用一：访问修饰符：open、final、abstract
 *                  open：可以继承、可以重写
 *                  final：不可继承、不可重写
 *                  abstract：必须继承、必须重写
 *                  普通类：类和方法默认都是final
 *                  抽象类：类、抽象属性、抽象方法默认为open，非抽象属性、非抽象方法为final
 *                  接  口：接口、属性、方法默认都是open，抽象属性和抽象方法默认都是abstract
 *                  注意：重写的方法和属性默认是open的
 *
 * */
class ChildClass : Animal(""), Clickable {


    /**
     * 抽象类
     * 1、抽象的成员必须重写
     * 2、非抽象的成员是final，不能重写
     * */
    override var weight: Float
        get() = 10.0f
        set(value) {}

    override fun voice() {
    }

    override fun swim() {
        super.swim()
    }
    //******抽象类******

    /**
     * 接口
     * 1、抽象的属性和方法默认是abstract，必须重写
     * 2、非抽象的是open，可以重写
     */
    override var canClick: Boolean = false
    override var isClick: Boolean
        get() {
            return super.isClick
        }
        set(value) {}

    override fun onClick() {
    }
    //******接口******

}

/**
 * 使用二：多实现
 *        实现多个接口可能遇到两个以上的接口包含相同的函数，所以我们在重写这样的方法的时候，需要指明这个方法的父类
 *        如下边的showOff函数，Clickable和Focusable接口都有这个函数，所以在实现这个函数的时候，需要通过super<父类>的方式来指定当前实现是谁
 *        当然也可以都指定
 *        不能不指定
 * */
interface Focusable {
    fun showOff() {
        println("Focusable showOff")
    }

}

class Button : Clickable, Focusable {
    override var canClick: Boolean = false

    override fun onClick() {
    }

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

/**
 * 使用三：继承中构造方法的用法
 *        同样，Kotlin中子类初始化，必须先初始化父类
 *        下面例子模拟java的view系统
 *        所以，从构造的重写，需要在子类的从构造的参数括号后边添加 :super(参数) 来初始父类
 *        且，可以使用 :this(参数) 来调用自己的构造
 * */
interface Context

interface AttributeSet
open class View {
    constructor(context: Context) {
        //some code
    }

    constructor(context: Context, attr: AttributeSet?) {
        //some code
    }

    constructor(context: Context, attr: AttributeSet?, id: Int) {
        //dome code
    }
}

class TextView : View {
    constructor(context: Context) : this(context, null) {
        //some code
    }

    constructor(context: Context, attr: AttributeSet?) : super(context, attr) {
        //some code
    }

    constructor(context: Context, attr: AttributeSet?, id: Int) : super(context, attr, id) {
        //some code
    }
}

/**
 * 使用四：类委托
 *          在实现一个接口或者实现一个类的时候，很多方法已经有子类A实现，且这里可以复用，那么这个类就可以把这些实现委托给子类A
 *          例如下边：
 *              自定义的DelegatingCollection类实现集合接口，然后把具体实现委托给ArrayList
 *              这样，DelegatingCollection里边如果重写哪些方法，那么这些方法就不委托给ArrayList
 * */

class DelegatingCollection<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {

}