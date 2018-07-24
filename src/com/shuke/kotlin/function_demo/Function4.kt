package com.shuke.kotlin.function_demo

import com.shuke.kotlin.bean.Persion

/**
 * @创建人 shukewei
 * @创建时间 2018/7/24 下午3:13
 * @类描述     函数：局部函数 -> 减少重复代码
 */

fun main(args: Array<String>) {
    /**
     * 使用一
     */
    savePersion(Persion("shuke", 28, ""))

    /**
     * 使用二
     */
    save(Persion("shuke", 28, ""))
}

/**
 * 使用一：局部函数
 *        即在函数中声明函数，可访问外部函数的所有参数和变量
 *        作用：抽取重复代码
 */
private fun savePersion(persion: Persion) {
    fun validate(fieldName: String, value: String) {
        if (value.isEmpty()) {
            println("can not save persion ${persion.name} because $fieldName is empty")
        }
    }
    validate("name", persion.name)
    validate("address", persion.address!!)
}

/**
 * 使用二：局部函数和扩展
 *        这里解释下：
 *          首先这个扩展方法时定义在保存Persion的类或者说文件中
 *          是因为只有保存的时候才需要验证
 *          而又把验证的逻辑放到Persion的扩展，是因为，验证的逻辑是术语Persion类的行为（封装）
 */
private fun Persion.validateBeforeSave() {
    fun validate(fieldName: String, value: String) {
        if (value.isEmpty()) {
            println("can not save persion $name because $fieldName is empty")
        }
    }
    validate("name", name)
    validate("address", address!!)
}

private fun save(persion: Persion){
    persion.validateBeforeSave()
    //开始存储
}