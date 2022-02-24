/**
 * Exception handling in Flow
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.exception_handling

import com.forever.bee.cold_asynchronous_data_stream.flowingRiver
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val damCapacity = 50;
    var waterLevel = 0

    flowingRiver()
        .onStart {
            println("River flowing...")
        }
        .onEach {
            check(waterLevel <= damCapacity) {
                "Dam capacity reached."
            }
            println("Water level ${++waterLevel}")
        }
        .onCompletion { cause ->
            cause?.let {
                println("The dam became full.")
            } ?: println("River is empty but the dam not full.")
        }
        .catch { e ->
            println("!!! ALERT !!! : ${e.message}")
        }.collect()
}
