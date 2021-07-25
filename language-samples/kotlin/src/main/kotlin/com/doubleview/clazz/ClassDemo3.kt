package com.doubleview.clazz

//================声明接口==========================
interface MyInterface {
    val prop: Int
    val propertyWithImplementation: String
        get() = "foo"
    fun bar()
    fun foo() {
    }
}

//实现接口
class Child : MyInterface {
    override val prop: Int = 29;
    override fun bar() {
    }
}

//================接口继承==========================
interface Named {
    val name : String
}

interface PersonDerive: Named {
    val firstName: String
    val lastName: String
    override val name: String get() = "$firstName $lastName"
}

data class Employee(override val firstName: String,
                    override val lastName: String) : PersonDerive {
}


//================接口继承==========================
interface AI {
    fun foo() { print("A") }
    fun bar()
}

interface BI {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class CI : AI {
    override fun bar() { print("bar") }
}

class DI : AI, BI {
    override fun foo() {
        super<AI>.foo()
        super<BI>.foo()
    }

    override fun bar() {
        super<BI>.bar()
    }
}

//================SAM  转换==========================
fun interface IntPredicate {
    fun accept(i: Int): Boolean
}
val isEven = object : IntPredicate {
    override fun accept(i : Int) : Boolean {
        return i % 2 == 0
    }
}
// 通过lambda表示式创建一个实例
val isEven2 = IntPredicate { it % 2 == 0 }
val isEvenTest = "Is 7 even ? ${isEven.accept(7)}"