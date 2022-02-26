/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val fruits = arrayOf("Apple", "Banana", "Tomatoes", "Pear", "Grapes", "Strawberry")

    val fChannel = Channel<String>()

    runBlocking {
        // Producer
        GlobalScope.launch {
            for (fruit in fruits) {
                // Send data in channel
                fChannel.send(fruit)
                // conditional close
                if (fruit == "Pear") {
                    // Signal the closure of channel
                    fChannel.close()
                }
            }
        }
        // Consumer
//        for (fruit in fChannel) {
//            println(fruit)
//        }
        while (!fChannel.isClosedForReceive) {
            val fruit = fChannel.receive()
            println(fruit)
        }
//        repeat(4) {
//            val fruit = fChannel.receive()
//            println(fruit)
//        }
        println("Done!")
    }
}

