/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

//                                  Pipeline
fun produceItemList() = GlobalScope.produce {
    val items = ArrayList<Characteristic>()
    items.add(Fruit("Apple", "Blue"))
    items.add(Fruit("Apple", "Red"))
    items.add(Vegetable("Zucchini", "Green"))
    items.add(Fruit("Grapes", "Green"))
    items.add(Vegetable("Radishes", "Red"))
    items.add(Fruit("Banana", "Yellow"))
    items.add(Fruit("Cherries", "Red"))
    items.add(Vegetable("Broccoli ", "Green"))
    items.add(Fruit("Strawberry", "Red"))
    items.add(Fruit("Tomatoes", "Red"))
    // send each item in the channel
    items.forEach {
        send(it)
    }
}

fun isFruit(items: ReceiveChannel<Characteristic>) = GlobalScope.produce {
    for (item in items) {
        // send each item in the channel only if it is a fruit
        if (isFruit(item)) {
            send(item)
        }
    }
}

fun isRed(items: ReceiveChannel<Characteristic>) = GlobalScope.produce {
    for (item in items) {
        // send each item in the channel only if it is red color.
        if (isRed(item)) {
            send(item)
        }
    }
}

fun main() = runBlocking {
        val itemsChannel = produceItemList()
        val fChannel = isFruit(itemsChannel)
        val redChannel = isRed(fChannel)

        for (item in redChannel) {
            print("${item.name}, ")
        }

        redChannel.cancel()
        fChannel.cancel()
        itemsChannel.cancel()

        println("Done!")
    }