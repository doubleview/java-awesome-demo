package com.doubleview

fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b:Int) = a + b

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun strTemplate() {
    var a = 1
    val s1 = "a is $a"
    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
}

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 `x * y` 会导致编译错误，因为它们可能为 null
    if (x != null && y != null) {
        // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null;
}

fun testFor() {
    val items = listOf("apple", "banana", "orange")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun testWhile() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

fun testRange() {
    val x = 1
    val y = 10
    if (5 in x..y) {
        println("fits int range")
    }

    val items = listOf("apple", "banana", "kiwifruit")
    if (-1 !in 0..items.lastIndex) {
        println("-1 is out of range")
    }

    for (z in 0..5) {
        println(z)
    }

    for(z in 1..100 step 2)
        println(z)

    for (z in 9 downTo 0 step 3) {
        print(z)
    }
}

fun testCollection() {
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    when {
        "orange" in fruits -> println("orange is in fruits")
        "apple" in fruits -> println("apple is fine too")
    }
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach{ println(it) }
}

fun main(args: Array<String>) {
    val a = 1
    val b: Int
    b = 2
    println("a = $a, b = $b")
    var x =  5;
    x += 1

    println(sum1(1, 2))
}