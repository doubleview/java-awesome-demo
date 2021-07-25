package com.doubleview

fun testSequence() {
    val oddNumbers = generateSequence(1) { it + 2 } // `it` 是上一个元素
    println(oddNumbers.take(5).toList())
    //println(oddNumbers.count())     // 错误：此序列是无限的。

    val oddNumbersLessThan10 = generateSequence(1) { if (it + 2 <= 10) it + 2 else null}
    println(oddNumbers.take(5).toList())

    val oddNumbers2 = sequence {
        yield(1)
        yield(3)
        yieldAll(listOf(5 , 7))
        yieldAll(generateSequence(9) { it + 2 })
    }
    println(oddNumbers2.take(5).toList())
}

fun testSequence2() {
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    // 将列表转换为序列
    val wordsSequence = words.asSequence()

    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
            .map { println("length: ${it.length}"); it.length }
            .take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // 末端操作：以列表形式获取结果。
    println(lengthsSequence.toList())
}

fun main() {
    testSequence();
}