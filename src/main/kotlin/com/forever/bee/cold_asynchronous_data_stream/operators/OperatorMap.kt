/**
 * Operators in Flow: map.- a terminal function.
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.operators

import com.forever.bee.cold_asynchronous_data_stream.flowingRiver
import com.forever.bee.cold_asynchronous_data_stream.pollutionByWeThePeople
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        flowingRiver()
            .map {
                pollutionByWeThePeople()
            }
            .collect { value ->
                println("Drop of salty water $value")
            }
    }
    delay(3000L)
    job.cancel()
}
