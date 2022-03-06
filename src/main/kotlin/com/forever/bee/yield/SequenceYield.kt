/**
 * Kotlin Coroutines: Coroutines with sequence & iterators
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.yield

fun main() {
    val seq = singleValuesSample()
    seq.forEach {
        println(it)
    }
}
/**
 * Single values sample using sequence DSL.
 * */
fun singleValuesSample() = sequence {
    println("The first value: ")
    yield("Apple")
    println("The second value: ")
    yield("Banana")
    println("The third value: ")
    yield("Tomatoes")
}