/**
 * Beyond coroutine Job - A Flow (Cold asynchronous data stream).
 * Canceling a Flow
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        flowingRiver().collect {
            value -> println("Drop of $value")
        }
    }
    delay(3000L)
    job.cancel()
}