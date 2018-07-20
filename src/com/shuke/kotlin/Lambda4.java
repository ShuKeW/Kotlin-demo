package com.shuke.kotlin;

import kotlin.jvm.functions.Function1;

/**
 * @创建人 shukewei
 * @创建时间 2018/7/20 下午4:48
 * @类描述 java中调用kotlin的高阶函数
 */
public class Lambda4 {

    public static void main(String[] args) {
        /**
         * 方法一：通过java8的lambda
         */
        String result1 = Lambda3Kt.filterString("a1s2d3f4", character -> character >= 'a' && character <= 'z');
        System.out.println("result1:"+result1);

        /**
         * 方法二：通过匿名内部类
         *        原理：一个函数类型被申明成接口FunctionN，N表示参数个数，比如Function1，Function2，都是kotlin定义好的接口
         */
        String result2 = Lambda3Kt.filterString("a1s2d3f4", new Function1<Character, Boolean>() {
            @Override
            public Boolean invoke(Character character) {
                return character >= 'a' && character <= 'z';
            }
        });
        System.out.println("result2:"+result2);
    }
}
