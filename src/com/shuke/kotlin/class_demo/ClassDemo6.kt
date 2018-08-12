package com.shuke.kotlin.class_demo

/**
 * Kotlin中的类：可见性修饰符
 *              java中：          类   包   子类  其他包
 *                  public        ✔   ✔    ✔    ✔
 *                  protect       ✔   ✔    ✔
 *                  default       ✔   ✔
 *                  private       ✔
 *
 *              Kotlin中：        类/文件   包   模块  子类  其他包
 *                  public        ✔       ✔    ✔    ✔    ✔
 *                  protected     ×       ✔    ✔    ✔
 *                  internal      ✔       ✔    ✔
 *                  private       ✔
 *              Kotlin中的默认修饰符是public，即不指定的都是public
 *              这里需要注意的属性的可见性，申明属性的可见性是和用在他的getter和setter，而支持字段仍然是private的。
 *              当然可以通过修改访问器的可见性来限制访问
 *              如果属性的可见性为private，那么就不会生成访问器getter和setter
 *
 *              java调用Kotlin中的可见性：
 *                  Kotlin中的public、protected、private在编译成java字节码的时候会被保留。
 *                  但是private类会被编译成default
 *                  internal会被编译成public
 *
 * */