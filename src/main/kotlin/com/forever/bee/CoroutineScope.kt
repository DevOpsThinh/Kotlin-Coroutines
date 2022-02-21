/**
 * Kotlin Coroutines: Understanding the scope
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default

//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch

/**
 * Creating simple coroutine using GlobalScope and try to observe the name
 */
//fun main() {
//    println("Thread : ${Thread.currentThread().name} Received an order to prepare burger "
//    + "& diet coke.")
//    GlobalScope.launch {
//        delay(2500)
//        println("Thread : ${Thread.currentThread().name} Preparing coke")
//    }
//    GlobalScope.launch {
//        delay(3500)
//        println("Thread : ${Thread.currentThread().name} Preparing burger")
//    }
//    println("Thread : ${Thread.currentThread().name} Next order please!")
//    Thread.currentThread().join()
//}

/**
 * Creating simple coroutine using CoroutineScope & try to observe the name
 * */
fun main() {
    println("Thread: StartingAJob.kt ${Thread.currentThread().name} Received an other to " +
            "prepare burger & diet coke.")
    CoroutineScope(Default).launch {
        delay(2500)
        println("Thread: ${Thread.currentThread().name} Preparing coke.")
    }
    CoroutineScope(Default).launch {
        delay(3500)
        println("Thread: ${Thread.currentThread().name} Preparing burger.")
    }
    println("Thread: (${Thread.currentThread().name}) Next order please!")
    Thread.currentThread().join()
}