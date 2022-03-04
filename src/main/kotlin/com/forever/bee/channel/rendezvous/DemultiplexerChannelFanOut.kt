/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel.rendezvous

import com.forever.bee.channel.Characteristic
import com.forever.bee.channel.Demultiplexer
import com.forever.bee.channel.isVegetable
import com.forever.bee.channel.produceItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        // Initialize the channels
        val kChannel = Channel<Characteristic>()

        val fChannel = Channel<Characteristic>()
        val vChannel = Channel<Characteristic>()

        launch {
            produceItems().forEach {
                kChannel.send(it)
            }
            kChannel.close()
        }
        // maps item
        val typeDemultiplexer = Demultiplexer(
            fChannel to { item: Characteristic ->
                com.forever.bee.channel.isFruit(item)
            },
            vChannel to { item: Characteristic ->
                isVegetable(item)
            }
        )

        launch {
            typeDemultiplexer.consume(kChannel)
        }
        launch {
            for (item in fChannel) {
                // Consume fruits channel
                println("${item.name} is a fruit.")
            }
        }
        launch {
            for (item in vChannel) {
                // Consume vegetables channel
                println("${item.name} is a vegetable.")
            }
        }
    }
}