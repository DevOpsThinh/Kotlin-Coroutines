/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel.comparing

import com.forever.bee.channel.Fruit
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val kChannel = Channel<String>()

    runBlocking {
        launch {
            for (i in Fruit.fruits) {
                if (i == "Pear") {
                    break
                }
                kChannel.send(i)
                println("Sent: $i")
            }
        }
        launch {
            repeat(Fruit.fruits.size) {
                val fruit = kChannel.tryReceive()

                if (fruit != null) {
                    println("Received: $fruit")
                } else {
                    println("Channel is empty!")
                }
                delay(500L)
            }
        }
        println("Done!")
    }
}
