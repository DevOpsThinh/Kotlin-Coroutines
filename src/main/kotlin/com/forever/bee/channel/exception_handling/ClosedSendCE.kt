/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel.exception_handling

import com.forever.bee.channel.Fruit
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val kChannel = Channel<String>()

    runBlocking {
        launch {
            for (i in Fruit.fruits) {
                try {
                    kChannel.send(i)
                } catch (e: Exception) {
                    println("Exception raised: ${e.javaClass.simpleName}")
                }
            }
            println("Done!")
        }

        repeat(Fruit.fruits.size - 1) {
            val fruit = kChannel.receive()
            // conditional close
            if (fruit == "Pear") {
                // signal that closure of channel
                kChannel.close()
            }
            println(fruit)
        }
    }
}