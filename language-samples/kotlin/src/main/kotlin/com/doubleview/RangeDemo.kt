package com.doubleview

class Version(val major: Int, val minor: Int) : Comparable<Version> {

    override fun compareTo(other: Version): Int {
        if (this.major != other.major) return this.major - other.major
        return this.minor - other.minor;
    }
}

fun rangeTst() {
    val versionRange = Version(1, 11)..Version(1, 30)
    println(Version(0, 9) in versionRange)
    println(Version(1, 20) in versionRange)
}

fun rangeTest2() {
    for(i in 1..9) println(i)

    (1..100 step 2).forEach { println(it) }
}