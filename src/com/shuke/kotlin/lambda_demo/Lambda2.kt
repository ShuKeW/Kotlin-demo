package com.shuke.kotlin.lambda_demo

import com.shuke.kotlin.lambda_demo.javaclass.View

/**
 * Lambda在java函数式接口中的使用
 */
fun main(args: Array<String>) {
    /**
     * 使用一
     */
    testFunctionInterface()
    /**
     * 使用二
     */
    println(testSAMConstructor())
    /**
     * 使用三
     */
    useSAMConstructor()
}

/**
 * 使用一：函数式接口
 *        定义：只有一个抽象方法的接口叫函数式接口，也叫SAM接口。例android中的OnClickLinstener。
 *        使用：kotlin中调用函数式接口作为参数的方法的时候，可以使用lambda作为函数式接口的实参
 *        原理：编译器遇到一个lambda实参的时候，会生成类，然后在调用处创建了一个匿名内部类
 *        反编译后的代码：
 *        class TestFunctionInterface$1(val button:ScrollView):OnClickListener{
 *              override fun onClick(val button:ScrollView){
 *                      //lambda的代码块作为了唯一方法的方法体
 *                      pringln(button)
 *              }
 *        }
 *        private fun testFunctionInterface(){
 *          val button = ScrollView()
 *          button.setOnClickListener(testFunctionInterface(button))
 *        }
 *        注意：java中每次调用testFunctionInterface的时候都会创建一个匿名内部类对象
 *             但是kotlin中（内联函数除外），如果lambda中没有捕捉变量，只会创建一次，捕捉了变量才会每次创建
 * */
private fun testFunctionInterface() {
    /**
     * kotlin中setOnclickListener的写法
     */
    val button = View()
    button.setOnClickListener {
        //onClick的逻辑
        println("${it}")
    }
}

/**
 * 使用二：SAM构造（可以创建一个借口的实例）
 *        构造了一个SAM接口的实例，构造方法是编译器自动生成的，且参数是唯一抽象方法的参数
 *        场景：Android中的OnClickListener
 *
 */
private fun testSAMConstructor(): View.OnClickListener {
    return View.OnClickListener {
        //onClick的逻辑
    }
}

/**
 * 使用三：SAM构造在Android中的优化使用场景
 */
private fun useSAMConstructor() {
    val btn1 = View()
    val btn2 = View()
    val clickListener = View.OnClickListener {
        when (it.id) {
            "R.id.btn1" -> println("btn1 onclick")
            "R.id.btn2" -> println("btn2 onclick")
            else -> println("unknow")
        }
    }
    btn1.setOnClickListener(clickListener)
    btn2.onClickListener = clickListener

}