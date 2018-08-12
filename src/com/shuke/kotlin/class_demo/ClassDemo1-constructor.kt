package com.shuke.kotlin.class_demo

/**
 * Kotin中的类：
 *          语法：关键字class
 *              class 类名（主构造方法）{
 *                  //类体
 *              }
 *          说明：Kotlin的类和java的类相比，构造方法和属性的定义不同
 */

fun main(args: Array<String>) {
    val user1 = User1("user1")
    val user2 = User2("user2")
    val user3 = User3("user3")
    val user4 = User4("user4")
    println("${user1.name} ${user2.name} ${user3.name} ${user4.name} ")


}

/**
 * 使用一：主构造
 *          kotlin中类的构造方法分为主构造和从构造
 *          主构造和从构造没有本质的区别，一个主要的区别是在类的内部还是外部申明的，在外部申明的为主构造，在内部申明的是从构造
 *          主构造的申明需要：
 *                         constructor(//构造方法的参数) -> 构造方法的申明，一般提供构造方法的参数
 *                         init{//类初始化时候执行的代码} -> 初始化语句块，在类初始化的时候执行，可以有多个
 *          在这个例子中：主构造方法提供一个参数_name，初始化语句块用_name来初始化属性name
 */
class User1 constructor(_name: String) {
    var name: String

    init {
        name = _name
    }
}

/**
 * 使用二：主构造的简化
 *          1、使用主构造的参数初始化属性，可以直接放在属性的申明中，不必放在初始化语句块里
 *          2、如果主构造方法没有注解和可见性修饰符，可以省略constructor关键字
 */
class User2(_name: String) {
    var name: String = _name
}

/**
 * 使用三：主构造的进一步简化
 *          1、在上一步的基础上，如果属性的名字和主构造的方法是一样的，可以省略属性的申明，把val/var修饰符提到主构造的参数
 *              注意：如果没有给主构造的参数加上val/var修饰符，说明这只是参数不是属性，所以类就不会生成对应的属性和getter和setter
 *
 *          总结：这三种主构造的本质是等价的，只不过写法上越来越简化
 *               编译出来的结果：
 *                  public final class User{
 *                      privare final String name;
 *                      public User(@Notnull String name//这里User1和User2为_name){
 *                          this.name = name;
 *                      }
 *                      public final String getName(){
 *                          return this.name
 *                      }
 *
 *                  }
 *
 *           并且：像User3这样没有{}的类，叫值对象类
 *
 *           注意：
 *              1、可以像函数那样为构造方法的参数提供默认值
 *              2、创建一个类的实例，直接调用构造方法（Kotlin中没有new关键字）
 *              3、默认无参构造：Kotlin不像java每个类都自动生成一个无参构造
 *                             Kotlin只有当没有申明构造和所有的构造都有默认值的情况下才生成无参的构造
 */
class User3(var name: String)

/**
 * 使用四：从构造
 *          之前说过从构造就是在类内写的构造方法。这个写法和java是比较类似的了。
 */
class User4 {
    var name: String

    constructor(name: String) {
        this.name = name
    }
}

