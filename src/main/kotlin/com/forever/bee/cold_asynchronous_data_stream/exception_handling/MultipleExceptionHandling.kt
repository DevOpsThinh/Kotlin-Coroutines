/**
 * Exception handling in Flow
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.exception_handling

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    numbers().catch { e ->
        println("Exception caught ${e.message}")
    }.collect {
        println(it)
    }
}

suspend fun numbers(): Flow<Int> {
    return (1..31)
        .asFlow()
        .onEach {
            delay(100L)
            if (it == 13) {
                throw Exception()
            }
        }
        .catch {
            throw Exception("Internet not available!")
        }
        .map {
            it * 2
        }
}