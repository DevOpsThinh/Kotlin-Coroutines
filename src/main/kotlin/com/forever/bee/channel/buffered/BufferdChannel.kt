/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel.buffered

import com.forever.bee.channel.Fruit
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val kBufferedChannel = Channel<String>(2)

    runBlocking {
        launch {
            for (i in Fruit.fruits) {
                kBufferedChannel.send(i)
                println("Produced: $i")
            }
            kBufferedChannel.close()
        }
        launch {
            for (i in kBufferedChannel) {
                println("Consumed: $i")
                delay(1_000L)
            }
        }
    }
}
