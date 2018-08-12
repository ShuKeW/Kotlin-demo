package com.shuke.kotlin.class_demo

/**
 * Kotlin中的类：一些其他的类
 * */



/**
 * 使用一：内部类(inner)和嵌套类(static,是编译成static，kotlin中没有static关键字)
 *        1、默认是嵌套类
 *              在一个类中定义类，默认是和java中的static内部类是一样
 *        2、inner内部类
 *              可以给嵌套类加上inner修饰符，让他变成内部类，就和java一样，内部类拥有外部类的实例
 * */

/**
 * 使用二：密封类(scaled)
 *          定义：把一个类申明成scaled，那么他的直接子类必须作为嵌套类定义在这个类中(这个限制在Kotlin1.1开始解除了)
 *          且，用了scaled修饰，类默认变为open的了
 *          用途：在when的分之中，条件是匹配这些子类，那么就不需要else分之
 * */

/**
 * 使用三：数据类(data)
 *          定义：把类申明成data class，编译器会帮我们自动生成equals、hashCode、toString方法
 *          因此，data class常用来比较，所以他的属性应该都为val，为了在比较的时候不会因为属性的变化而影响对象的比价结果
 *          所以，要想修改对象的内容，一般定义copy方法来把属性复制过来成为一个新的对象
 *          copy的写法是:
 *              class Client(val name:String,val postCode:Int){
 *                  fun copy(name:String = this.name,postCode:Int = this.postCode){
 *                      Client(name,postCode)
 *                  }
 *              }
 *              这样，当不需要改变属性的时候直接使用默认参数即可
 * */
