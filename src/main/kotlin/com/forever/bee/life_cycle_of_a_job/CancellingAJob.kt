/**
 * Kotlin Coroutines: Cancelling a Job
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.life_cycle_of_a_job

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.abs
import kotlin.random.Random

fun main() {
    runBlocking {
        val orderJob = CoroutineScope(IO).launch {
            println(
                "Placing your order fo ${
                    listOf(
                        "Boiled Vegetable Salad",
                        "Mushroom curry",
                        "Punjabi flat bread"
                    )
                }"
            )
            delay(5000)
            val orderId = "ORDER_${abs(Random(9999999).nextInt()) }"
            println("Your order has  been placed, order id is $orderId")
        }
        delay(3500)
        println("Customer is tired of waiting & now cancelling the order")
        orderJob.cancel()
    }
}