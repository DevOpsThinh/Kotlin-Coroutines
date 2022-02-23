/**
 * Kotlin Coroutines: Managing asynchronous coroutine
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.life_cycle_of_a_job

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlin.random.Random

fun main() = runBlocking {
    val orderJob = launch(Default){
        while (isActive) {
            println("Placing your order for ${listOf("Boiled vegetable Salad", "Mushroom curry", "Punjabi flat bread")}")
            println("Your order has been placed, order id is ORDER_${Random(9999999).nextInt()}")
        }
    }
    delay(5000)
    orderJob.cancelAndJoin()
    println("Job is cancelled")
    delay(2500)
}

//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (i < 5) {
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("Job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main: Now I can quit.")
//}




