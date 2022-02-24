/**
 * Kotlin Coroutines:  Measuring coroutine's performance.
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    repeat(5) {
        sumWithAsync()
        sumWithoutAsync()
    }
}
/**
 * A single synchronous task
 * */
fun sumWithoutAsync() = runBlocking {
    val totalTime = measureTimeMillis {
        val sum1 =  sum(1, 300)
        val sum2 = sum(301, 600)
        val sum3 = sum(601, 900)
        val sum4 = sum(901, 1200)
        val sum5 = sum(1201, 1500)
        println("Sum is ${sum1 + sum2 + sum3 + sum4 + sum5}")
    }
    println("Total time without coroutine: $totalTime")
}
/**
 * Five asynchronous tasks
 * */
fun sumWithAsync() = runBlocking {
    val totalTime = measureTimeMillis {
        val sum1 = async { sum(1, 300) }
        val sum2 = async { sum(301, 600) }
        val sum3 = async { sum(601, 900) }
        val sum4 = async { sum(901, 1200) }
        val sum5 = async { sum(1201, 1500) }
        println("Sum is ${sum1.await() + sum2.await() + sum3.await() + sum4.await() + sum5.await()}")
    }
    println("Total time with coroutine: $totalTime")
}

private suspend fun sum(startNum: Int, endNum: Int): Int {
    delay(120)
    return (startNum..endNum).sum()
}