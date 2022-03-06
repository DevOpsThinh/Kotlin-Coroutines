/**
 * Exception handling in Flow
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.exception_handling

import com.forever.bee.cold_asynchronous_data_stream.flowingRiver
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val damCapacity = 50;
    var waterLevel = 0
    try {
        flowingRiver().collect {
            if (waterLevel >= damCapacity) {
                throw IllegalStateException("Dam capacity reached!")
            }
            println("Water level ${++waterLevel}")
        }
    } catch (e: Exception) {
        println("!!! ALERT !!! : ${e.message}")
    } finally {
        println("Water level logged & action taken.")
    }
}

//fun main() = runBlocking {
//    val damCapacity = 50; var waterLevel = 0
//    try {
//        flowingRiver().collect{
//            check(waterLevel <= damCapacity) {
//                "Dam capacity reached"
//            }
//            println("Water level ${++waterLevel}")
//        }
//    } catch (e: Exception) {
//        println("!!! ALERT !!! : ${e.message}")
//    } finally {
//        println("Water level logged & action taken.")
//    }
//}