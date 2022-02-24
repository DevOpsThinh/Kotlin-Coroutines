/**
 * Operators in Flow: buffer (optimal operator)
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.operators

import com.forever.bee.cold_asynchronous_data_stream.downloadVideo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTaken = measureTimeMillis {
        downloadVideo().collect {
            println("Downloading video $it%")
            delay(1000L)
        }
    }
    println("===== Total time to execute is $timeTaken =====")

    val timeTakenWithBuffer = measureTimeMillis {
        downloadVideo().buffer().collect {
            println("Downloading video $it%")
            delay(1000L)
        }
    }
    println("===== Total time to execute is $timeTakenWithBuffer =====")
}