/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel.rendezvous

import com.forever.bee.channel.Characteristic
import com.forever.bee.channel.isVegetable
import com.forever.bee.channel.produceItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        val desChannel = Channel<Characteristic>()

        val fChannel = Channel<Characteristic>()
        val vChannel = Channel<Characteristic>()

        launch {
            produceItems().forEach {
                if (com.forever.bee.channel.isFruit(it)) {
                    fChannel.send(it)
                }
            }
        }
        launch {
            produceItems().forEach {
                if (isVegetable(it)) {
                    vChannel.send(it)
                }
            }
        }

        launch {
            for (i in fChannel) {
                desChannel.send(i)
            }
        }
        launch {
            for (i in vChannel) {
                desChannel.send(i)
            }
        }

        desChannel.consumeEach {
            if (com.forever.bee.channel.isFruit(it)) {
                println("${it.name} is a fruit.")
            } else if (isVegetable(it)) {
                println("${it.name} is a vegetable.")
            }
        }

        coroutineContext.cancelChildren()
    }
}