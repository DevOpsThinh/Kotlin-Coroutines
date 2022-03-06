/**
 * Kotlin Coroutines:  Suspending a function
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    runBlocking {
        val orderJob = CoroutineScope(IO).launch {
            val orderId = prepareOrder(
                listOf(
                    "Boiled vegetable Salad",
                    "Mushroom curry",
                    "Punjabi flat bread"
                )
            )
            println("Your order has been placed, order id is $orderId")
        }
        orderJob.join()
    }
}

private suspend fun prepareOrder(orderList: List<String>): String {
    println("Placing your order for $orderList")
    delay(3000L)
    return "ORDER_${Random(9999999).nextInt()}"
}