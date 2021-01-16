package com.doubleview.clazz

class Person constructor(firstName: String) {}

class Person2(firstName: String)

class Person3(
        val firstName: String,
        val lastName: String,
        var age: Int) {}

class Person4(name: String){
    var children: MutableList<Person4> = mutableListOf()

    constructor(name: String, parent: Person4) : this(name) {
        parent.children.add(this)
    }
}

class Customer public constructor(name: String)

class InitOrderDemo(name: String) {
    val firstProperty  = "first property : $name".also(::println)

    init {
        println("first initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}


open class Base(val name: String) {
    init {
        println("Initializing Base")
    }

    open val size: Int = name.length.also { println("Initializing size in Base : $it") }
}

class Derived(name: String, val lastName: String) : Base(name.capitalize().also { println("Argument for Base: $it") }) {
    init {
        println("Initializing Derived")
    }

    override val size: Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

open class Shape {
    open val vertexCount: Int = 0
    open fun draw() {
    }

    fun fill() {

    }
}

class Circle : Shape() {
    override val vertexCount = 4
    override fun draw() {
        super.draw()
    }
}

//内部类访问外部超类
open class Rectangle {
    open fun draw() {
        println("Drawing a rectangle")
    }

    val borderColor:String get() = "black"
}

class FilledRectangle : Rectangle() {
    override fun draw() {
        super.draw();
    }
    val fillColor: String get() = super.borderColor
}

class FilledRectangle2 : Rectangle() {
    override fun draw() {
        val filler = Filler()
        filler.drawAndFill()
    }

    inner class Filler {
        fun fill() {
            println("Filling")
        }
        fun drawAndFill() {
            super@FilledRectangle2.draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle2.borderColor}")
        }
    }
}


// 覆盖规则
open class Base1 {
    open fun draw() {}
}
interface Base2 {
    fun draw() {}
}

class Square() : Base1(), Base2 {
    override fun draw() {
        super<Base1>.draw()
        super<Base2>.draw()
    }
}


// 抽象类
abstract class AbsTest {
    abstract fun draw()
}
