/**
 * Beyond coroutine Job - A Flow (Cold asynchronous data stream).
 * Emit & collect a Flow
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
//    flowingRiver().collect { value ->
//        println("Drop of $value")
//    }
    flowE().collect {
        value -> println("Drop of $value")
    }
}

fun flowE(): Flow<String> = flowOf(listOf(1..100_000_000)).map {
    it.toString()
}


