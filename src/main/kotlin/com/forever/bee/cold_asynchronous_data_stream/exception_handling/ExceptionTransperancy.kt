/**
 * Exception handling in Flow
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.exception_handling

import com.forever.bee.cold_asynchronous_data_stream.flowingRiver
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val damCapacity = 50
    val damWarningLevel = 45
    //val damWarningLevel = 45
    var waterLevel = 0

    flowingRiver()
        .onEach {
            check(waterLevel <= damWarningLevel) {
                "Water is higher than warning level."
            }
        }
        .catch { e ->
            println("!!! ALERT !!! : ${e.message}")
        }
        .onEach {
            check(waterLevel <= damCapacity) {
                "Dam capacity reached."
            }
        }
        .catch { e ->
            println("!!! HIGH ALERT !!! : ${e.message}")
        }
        .collect {
            println("Water level ${++waterLevel}")
        }
}

//fun main() = runBlocking {
//    val damCapacity = 50;
//    var waterLevel = 0
//    flowingRiver()
//        .onEach {
//            check(waterLevel <= damCapacity) {
//                "Dam capacity reached."
//            }
//        }
//        .catch { e ->
//            println("!!! ALERT !!! : ${e.message}")
//        }
//        .collect {
//            println("Water level ${++waterLevel}")
//        }
//}