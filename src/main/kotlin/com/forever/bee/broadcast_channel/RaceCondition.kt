/**
 * Kotlin Coroutines: Broadcast Channels Section
 * References:
 * https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-broadcast-channel/index.html
 * (This API is obsolete since 1.5.0. It will be deprecated with warning in 1.6.0 and with error in 1.7.0. It is replaced with SharedFlow.)
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.broadcast_channel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val kChannel = Channel<String>()

    runBlocking {
        // Producer
        GlobalScope.launch {
            // send data in channel
            kChannel.send(fruits[0])
        }
        // consumers
        GlobalScope.launch {
            kChannel.consumeEach { value ->
                println("Consumer 1: $value")
            }
        }
        GlobalScope.launch {
            kChannel.consumeEach { value ->
                println("Consumer 2: $value")
            }
        }
        GlobalScope.launch {
            kChannel.consumeEach { value ->
                println("Consumer 3: $value")
            }
        }
        // Wait for a keystroke to exit the program
        println("Press a key to exit...")
        readLine()
        // Close the channel to cancel the consumers on it too.
        kChannel.close()

    }
}