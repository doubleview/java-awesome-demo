package com.doubleview.clazz

//================可见性==========================
var bar: Int = 5

open class Outer {
    internal var a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    override val b = 5
}

class Unrelated(o: Outer)


//================ 扩展 ==========================
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

val list = mutableListOf(1, 2, 3).swap(0, 2)

// 扩展是静态解析的
open class Shape2

class Rectangle2: Shape()

fun Shape.getName() = "Shape"

fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName()) //这里会输出  Shape, 取决于参数s的类型
}

//优先取成员函数
fun extensionTest() {
    class Example {
        fun printFunctionType() { println("Class method") }
    }

    fun Example.printFunctionType() { println("Extension function") }

    Example().printFunctionType()
}

// 可空接受者
fun Any?.toString(): String {
    if(this == null)
        return "null"
    return toString()
}

///扩展属性
