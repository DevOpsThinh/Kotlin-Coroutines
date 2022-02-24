/**
 * Operators in Flow: combine
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.operators

import com.forever.bee.cold_asynchronous_data_stream.getIngredients
import com.forever.bee.cold_asynchronous_data_stream.getUnitValues
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        getUnitValues()
            .onEach {
                delay(100L)
            }
            .combine(getIngredients().onEach {
                delay(500L)
            }) { unit, ingredient ->
                "$unit $ingredient"
            }
            .collect {
                println(it)
            }
    }
    job.join()
}