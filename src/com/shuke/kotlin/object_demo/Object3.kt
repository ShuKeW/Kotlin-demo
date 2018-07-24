package com.shuke.kotlin.object_demo

import com.shuke.kotlin.object_demo.javaClass.ScrollView

/**
 * @创建人 shukewei
 * @创建时间 2018/7/24 下午5:02
 * @类描述     匿名对象：对象表达式 -> 改变写法的匿名内部类
 *            学习lambda的时候，对于函数式接口，可以用lambda替代
 *            但是，当接口出现多个抽象方法的时候，怎么用kotlin来写呢？就是匿名对象
 *            即在object后边不指定名字
 *            这里万分注意：匿名对象不是单例的
 */

fun main(args: Array<String>) {
    val scrollView = ScrollView()
    scrollView.setOnScrollListener(object : ScrollView.OnScrollListener {
        override fun onScrolling() {

        }

        override fun onScrolled() {
        }

    })
}


