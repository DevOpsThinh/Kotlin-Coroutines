/**
 * Kotlin Coroutines: Coroutines with sequence & iterators
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.yield

fun main() {
    val seq = fibonacciGenerator().take(10)
    seq.forEach {
        println(it.toString())
    }
}
/**
 * Fibonacci numbers generator using sequence DSL.
 * */
fun fibonacciGenerator() = sequence {
    print("Suspending...")

    yield(0)

    var current = 0
    var next = 1

    while (true) {
        print("Suspending...")

        yield(next)

        val temp = current + next
        current = next
        next = temp
    }
}