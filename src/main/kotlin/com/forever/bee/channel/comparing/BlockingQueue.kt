/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel.comparing

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.util.concurrent.LinkedBlockingQueue

fun main() {
    val queue = LinkedBlockingQueue<Int>()
    runBlocking {
        launch {
            (1..7).forEach {
                queue.put(it)
                yield()
                println("Produced: $it")
            }
        }
        launch {
            while (true) {
                println("Consumed: ${queue.take()}")
                yield()
            }
        }
        println("Done!")
    }
}