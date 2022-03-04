/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

typealias Predicate<E> = (E) -> Boolean
typealias Rule<E> = Pair<Channel<E>, Predicate<E>>

interface Characteristic {
    val name: String
    val color: String
}

/**
 * Implementing a de-multiplexer. It sends each item into the first channel with a predicate
 * that evaluates true for it.
 * */
class Demultiplexer<E>(vararg val rules: Rule<E>) {

    suspend fun consume(receiveChannel: ReceiveChannel<E>) {
        for (i in receiveChannel) {
            // receive the data from the channel
            for (r in rules) {
                // check every rule until u find a successful one
                if (r.second(i)) {
                    r.first.send(i)
                }
            }
        }
        /*
        * When here the channel has been closed so u can close the de-multiplexed channels.
        * */
        closeAll()
    }

    /**
     * closes all the de-multiplexed channels
     * */
    private fun closeAll() {
        rules.forEach {
            it.first.close()
        }
    }
}

data class Fruit(override val name: String, override val color: String) : Characteristic {
    companion object Fruits{
        val fruits = arrayOf("Apple", "Tomatoes", "Banana", "Pear", "Grapes", "Strawberry")
    }
}
data class Vegetable(override val name: String, override val color: String) : Characteristic

//                                  Helper functions
fun isFruit(item: Characteristic): Boolean = item is Fruit
fun isRed(item: Characteristic): Boolean = (item.color == "Red")
fun isVegetable(item: Characteristic) = item is Vegetable

/**
 * Produces a finite number of items which are either a fruit or vegetable.
 * */
fun produceItems(): ArrayList<Characteristic> {
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
    items.add(Vegetable("Red bell pepper", "Red"))

    return items
}