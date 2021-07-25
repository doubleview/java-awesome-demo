package com.doubleview.clazz

//================声明属性==========================
class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"

    var allByDefault: Int? = null

    //自定义getter
    val size = 0
    val isEmpty get() = this.size == 0

    //自定义setter
    var stringRepresentation: String
        get() = this.toString()
        set(value) {
        }
    var setterVisibility:String = "abc"
        private set
}
const val SUBSYSTEM_DEPRECATED: String = "this subsystem is deprecated"
//延迟初始化
class MyTest {
    lateinit var subject: Address

    fun test() {
        if (MyTest()::subject.isInitialized) {
            println("initialized")
        }
    }
}



//================对象表达式==========================
open class A(x: Int) {
    open val y: Int = x
}
interface B {}

val ab: A = object : A(1), B {
    override val y = 15
}


//对象表达式用在函数内部
fun foo() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    println(adHoc.x + adHoc.y)
}

// 对象表示式用在私有函数和共有函数中
class C {
    //私有函数，返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    //共有函数，返回类型是Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x
//        val x2 = publicFoo().x
    }
}

//================对象声明-单例模式==========================

object DataProviderManager {
    val allDataProviders: Collection<DataProviderManager>? get() = listOf()

    fun registerDataProvider(provider: DataProviderManager?) {
    }
}

fun test() {
    DataProviderManager.registerDataProvider(null)
}

//伴生对象
class MyClass {
    companion object {
        fun create(): MyClass = MyClass()
    }
}
val instance = MyClass.create()
