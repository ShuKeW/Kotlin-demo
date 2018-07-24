@file:JvmName("JavaName")

package com.shuke.kotlin.function_demo

/**
 * @创建人 shukewei
 * @创建时间 2018/7/24 下午2:05
 * @类描述     函数：顶层函数和属性->替代静态工具类和常量类
 *              调用：1、Kotlin中，同一个包直接使用，不同的包需要先导包或者全路径名
 *                   2、java中，通过xx.kt文件编译成的xxKt类来访问，或者使用注解@JvmName
 *
 */

/**
 * 使用一：定义不可变的顶层属性
 *        原理：编译成 private static final 的属性，提供getter方法，无setter方法
 *        注意：对于非const的顶层属性，默认编译成private的，就算显示指定为public还是编译成private，通过getter和setter访问
 */
val valAttr = "valAttr"

/**
 * 使用二：定义可变的顶层属性
 *        原理：编译成 private static 的属性，提供getter、setter方法
 */
var varAttr = "varAttr"

/**
 * 使用三：const val(不能声明称const var）
 *        原理：编译成 public static final
 */
const val constValAttr = "constValAttr"

/**
 * 使用四：申明顶层函数
 *        原理：编译成 public static final 方法
 */
fun function(arg: String) {

}