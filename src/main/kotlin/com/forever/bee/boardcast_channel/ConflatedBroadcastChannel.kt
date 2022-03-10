/**
 * Kotlin Coroutines: Broadcast Channels Section
 * References:
 * https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-broadcast-channel/index.html
 * (This API is obsolete since 1.5.0. It will be deprecated with warning in 1.6.0 and with error in 1.7.0. It is replaced with SharedFlow.)
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.boardcast_channel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@OptIn(ObsoleteCoroutinesApi::class)
fun main() {
    val kChannel = ConflatedBroadcastChannel<String>()

    runBlocking {
        kChannel.apply {
            send(fruits[0])
            send(fruits[1])
            send(fruits[2])
        }

        GlobalScope.launch {
            kChannel.consumeEach { value ->
                println("Consumer 1: $value")
            }
        }
        GlobalScope.launch {
            kChannel.consumeEach { value ->
                println("consumer 2: $value")
            }
        }

        kChannel.apply {
            send(fruits[3])
            send(fruits[4])
        }

        println("Press a key to exit.....")
        readLine()

        kChannel.close()
    }
}