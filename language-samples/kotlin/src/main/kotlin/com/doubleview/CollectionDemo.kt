package com.doubleview

import java.util.*

fun printAll(strings: Collection<String>) {
    for(s in strings) print(s)
    println()
}

fun List<String>.getShortWordsTo(shortWords: MutableCollection<String>, maxLength: Int) {
    this.filterTo(shortWords) { it.length < maxLength }
    val articles = setOf("a", "A")
    shortWords -= articles
}

data class Person(var name: String, var age: Int)

fun testList() {
    val bob = Person("Bob", 31)
    val people = listOf(Person("Adam", 20), bob, bob)
    val people2 = listOf(Person("Adam", 20), Person("Bob", 31), bob)
    println(people == people2)
    bob.age = 32
    println(people == people2)

    val numbers = mutableListOf(1, 2, 3, 4)
    numbers[0] = -1
    numbers.add(1, 20)
    numbers.shuffle()
    println(numbers)

    val doubledList = List(3, { it * 2 })
    println(doubledList)
    val linkedList = LinkedList<Int>(listOf(1, 2, 3))

    val sourceList = mutableListOf<String>("a", "b", "c")
    val copyList = sourceList.toMutableList()
    val readOnlyList = sourceList.toList()

}

fun testSet() {
    val numbers = setOf(1, 2, 3, 4)
    println("Number of elements: ${numbers.size}")
    if (numbers.contains(1)) println("1 is in the set")

    val numbersBackwards = setOf(4, 3, 2, 1)
    println("The sets are equal: ${numbers == numbersBackwards}")
}

fun testMap() {
    val map = mutableMapOf("key1" to 1, "key2" to 2, "key3" to 3)
    map["key4"] = 4
    println("all keys is ${map.keys}")
    println("all values is ${map.values}")

    val map2 = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
    println("map and map1 equal ${map == map2}")


    val numbers = setOf(1, 2, 3)
    println(numbers.map { it * 3 })
    println(numbers.mapIndexed { idx, value -> value * idx })

    println(listOf("one", "two", "three", "four").associateWith { it.length })
}


fun mapTest() {
    val set = setOf(1, 2, 3)
    println(set.map { it * 3 })
    println(set.mapIndexed { idx, value -> value * idx })

    println(set.mapNotNull { it * 3 })
    println(set.mapIndexedNotNull { idx, value -> value * idx })

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
    println(numbersMap.mapKeys { it.key.toUpperCase() })
    println(numbersMap.mapValues { it.value * 10 })

}

fun zipTest() {
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)

    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals))

    println(colors.zip(animals) { color, animal -> "$color -> $animal" })

    val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
    println(numberPairs)
    println(numberPairs.unzip())
}

fun associateTest() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.associateWith { it.length })

    println(numbers.associateBy { it.first().toUpperCase() })
    println(numbers.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))

    data class FullName (val firstName: String, val lastName: String)

    fun parseFullName(fullName: String): FullName {
        val nameParts = fullName.split(" ")
        if (nameParts.size == 2) {
            return FullName(nameParts[0], nameParts[1])
        } else throw Exception("Wrong name format")
    }

    val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
    println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })
}

fun flattenTest() {
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten())

    data class StringContainer(val values: List<String>)
    val containers = listOf(
            StringContainer(listOf("one", "two", "three")),
            StringContainer(listOf("four", "five", "six")),
            StringContainer(listOf("seven", "eight"))
    )
    println(containers.flatMap { it.values })
}


fun joinStringTest() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))

    val numbers2 = (1..100).toList()
    println(numbers2.joinToString(limit = 10, truncated = "<...>"))

    val numbers3 = listOf("one", "two", "three", "four")
    println(numbers3.joinToString { "Element: ${it.toUpperCase()}"})
}

fun filterTest() {
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 }
    println(longerThan3)

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
    println(filteredMap)
}

fun filterTest2() {
    val numbers = listOf("one", "two", "three", "four")

    val filteredIdx = numbers.filterIndexed { index, s -> (index != 0) && (s.length < 5)  }
    val filteredNot = numbers.filterNot { it.length <= 3 }

    println(filteredIdx)
    println(filteredNot)
}

fun filterTest3() {
    val numbers = listOf(null, 1, "two", 3.0, "four")
    println("All String elements in upper case:")
    numbers.filterIsInstance<String>().forEach {
        println(it.toUpperCase())
    }
}

fun partitionTest() {
    val numbers = listOf("one", "two", "three", "four")
    val (match, rest) = numbers.partition { it.length > 3 }

    println(match)
    println(rest)
}

fun matchTest() {
    val numbers = listOf("one", "two", "three", "four")

    println(numbers.any { it.endsWith("e") })
    //没有任何一个匹配，则返回true
    println(numbers.none { it.endsWith("a") })
    println(numbers.all { it.endsWith("e") })

    println(emptyList<Int>().all { it > 5 })   // vacuous truth
}

fun plusTest() {
    val numbers = listOf("one", "two", "three", "four")

    val plusList = numbers + "five"
    val minusList = numbers - listOf("three", "four")
    println(plusList)
    println(minusList)
}

fun groupTest() {
    val numbers = listOf("one", "two", "three", "four", "five")

    println(numbers.groupBy { it.first().toUpperCase() })
    println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))

    val numbers2 = listOf("one", "two", "three", "four", "five", "six")
    println(numbers2.groupingBy { it.first() }.eachCount())
}

fun sliceTest() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.slice(1..3))
    println(numbers.slice(0..4 step 2))
    println(numbers.slice(setOf(3, 5, 0)))
}

fun takeDropTest() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.take(3))
    println(numbers.takeLast(3))
    println(numbers.drop(1))
    println(numbers.dropLast(5))
}

fun chunkTest() {
    val numbers = (0..13).toList()
    println(numbers.chunked(3) { it.sum() })  // `it` 为原始集合的一个块
}

fun windowTest() {
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.windowed(3))

    val numbers2 = (1..10).toList()
    println(numbers2.windowed(3, step = 2, partialWindows = true))
    println(numbers2.windowed(3) { it.sum() })

    val numbers3 = listOf("one", "two", "three", "four", "five")
    println(numbers3.zipWithNext())
    println(numbers3.zipWithNext() { s1, s2 -> s1.length > s2.length})
}

fun sortTest() {
    val list = mutableListOf("one", "two", "three")
    list.sort()
    println(list)

    //不改变原有集合，返回一个新集合
    val sortedList = list.sorted()
    println(list == sortedList)


    //自然顺序
    val numbers = listOf("one", "two", "three", "four")
    println("Sorted ascending: ${numbers.sorted()}")
    println("Sorted descending: ${numbers.sortedDescending()}")

    //自定义顺序
    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    println(listOf("aaa", "bb", "c").sortedWith(lengthComparator))
    println(listOf("aaa", "bb", "c").sortedWith(compareBy { it.length }))

    val numbers2 = listOf("one", "two", "three", "four")
    val sortedNumbers = numbers2.sortedBy { it.length }
    println("Sorted by length ascending: $sortedNumbers")
    val sortedByLast = numbers2.sortedByDescending { it.last() }
    println("Sorted by the last letter descending: $sortedByLast")

    //随机顺序
    val numbers3 = listOf("one", "two", "three", "four")
    println(numbers3.shuffled())
}

fun aggregationTest() {
    val numbers = listOf(6, 42, 10, 4)
    println("Count: ${numbers.count()}")
    println("Max: ${numbers.max()}")
    println("Min: ${numbers.min()}")
    println("Average: ${numbers.average()}")
    println("Sum: ${numbers.sum()}")

    val min3Remainder = numbers.minBy { it % 3 }
    println(min3Remainder)
    val strings = listOf("one", "two", "three", "four")
    val longestString = strings.maxWith(compareBy { it.length })
    println(longestString)

    val numbers2 = listOf(5, 42, 10, 4)
    println(numbers2.sumBy { it * 2 })
    println(numbers2.sumByDouble { it.toDouble() })

    println(numbers2.reduce{sum, element -> sum + element})
    println(numbers2.fold(0) { sum, element -> sum + element * 2 })

    println(numbers2.foldIndexed(0) { idx, sum, element -> if (idx % 2 == 0) sum + element else sum })
    println(numbers2.foldRightIndexed(0) { idx, element, sum -> if (idx % 2 == 0) sum + element else sum })
}

fun addTest() {
    val numbers = mutableListOf("one", "two")
    numbers += "three"
    println(numbers)
    numbers += listOf("four", "five")
    println(numbers)

    val numbers2 = mutableListOf("one", "two", "three", "three", "four")
    numbers2 -= "three"
    println(numbers2)
    numbers2 -= listOf("four", "five")
    println(numbers2)
}

fun main() {
    aggregationTest()
}