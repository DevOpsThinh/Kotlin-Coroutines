/**
 * Kotlin Coroutines: Managing asynchronous coroutine
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val deferredResult = async {
        delay(2500L)
        "Veggie treat"
    }
    println("Your coke is ready, waiting for burger....")
    println("Here is your burger, ${deferredResult.await()}")
}

//fun main() = runBlocking {
//    launch {
//        delay(3000L)
//        println("Here is your burger veggie treat.")
//    }
//    println("Here is your coke.")
//}

//fun main() = runBlocking {
//    delay(2500L)
//    println("Here is your coke & burger veggie treat.")
//}

