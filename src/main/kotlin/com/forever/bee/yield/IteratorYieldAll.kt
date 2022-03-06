/**
 * Kotlin Coroutines: Coroutines with sequence & iterators
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.yield

fun main() {
    val seq = sequentiallyIntegerIterableSample()
    seq.forEach {
        print("$it ")
    }
    println()
    val anoSequence = anotherIntegerIterableSample().take(10)
    anoSequence.forEach {
        print("$it ")
    }
}
/**
 * Generating infinite integers using  sequence DSL & the generateSequence() function.
 * */
fun anotherIntegerIterableSample() = sequence {
    yieldAll(generateSequence(2) { it * 2 })
}
/**
 * Integer numbers sample using sequence DSL.
 * */
fun sequentiallyIntegerIterableSample() = sequence {
    yieldAll(1..7)
}
