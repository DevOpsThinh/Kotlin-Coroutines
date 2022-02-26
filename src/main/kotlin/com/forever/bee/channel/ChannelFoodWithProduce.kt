/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
fun main() {
    val fruits = arrayOf("Apple", "Banana", "Tomatoes", "Pear", "Grapes", "Strawberry")

    fun produceFruits() = GlobalScope.produce<String> {
        for (fruit in fruits) {
            send(fruit)

                // Conditional close
            if (fruit == "Tomatoes") {
                // Signal that closure of channel
                close()
            }
        }
    }
    runBlocking {
        val fruits = produceFruits()
        fruits.consumeEach {
            println(it)
        }
        println("Done!")
    }
}