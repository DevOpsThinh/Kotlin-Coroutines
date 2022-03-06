/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
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
                // conditional close
                if (i == "Pear") {
                    // signal that closure of channel
                    kChannel.close()
                }
                kChannel.send(i)
            }
        }

        repeat(Fruit.fruits.size) {
            try {
                val fruit = kChannel.receive()
                println(fruit)
            } catch (e: Exception) {
                println("Exception raised: ${e.javaClass.simpleName}")
            }
        }
        println("Done!")
    }
}