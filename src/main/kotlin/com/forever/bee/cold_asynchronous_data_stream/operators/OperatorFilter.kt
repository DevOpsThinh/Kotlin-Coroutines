/**
 * Operators in Flow: filter - a terminal function.
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.operators

import com.forever.bee.cold_asynchronous_data_stream.flowingRiver
import com.forever.bee.cold_asynchronous_data_stream.pollutionByWeThePeople
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
            flowingRiver()
                .map {
                    pollutionByWeThePeople()
                }.filter {
                    it.pH >= 7
                }.collect { value ->
                    println("Drop of drinkable water $value")
                }
        }
        delay(3000L)
        job.cancel()
    }
}