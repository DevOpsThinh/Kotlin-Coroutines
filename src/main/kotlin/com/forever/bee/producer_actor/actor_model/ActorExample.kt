/**
 * Kotlin Coroutines:  Producer & Actors Section.
 * --------------------------------------------------------------------------------------------------------------------
 * Actor model: Another paradigm of multithreaded communication is when u are trying to delegate events to others,
 * such as work that u need to complete, => Sharing data in a multithreading environment.
 *
 *                     ----------->  Manager <-------------
 *                     |              actor               |
 *          Send main  |                |                 | Distribute
 *            task     |                |                 | subtasks to                      Actor's Diagram
 *                     |                |                 | workers and                (The Joy of Kotlin - Manning)
 *                     |                |                 | receive results.
 *                   Main               | Send sub        |
 *                   actor              | results to      |
 *                     ^                | receiver.       |-----> Worker actor.
 *                     |                |                 |
 *                     |                |                 |
 * Collate results and |                |                 |-----> Worker actor.
 * send back to main   |                |                 |
 *      actor.         |                v                 |
 *                     |-----------Receiver               |-----> Worker actor.
 *                                  actor                 |
 *                                                        |
 *                                                        |-----> Worker actor.
 * --------------------------------------------------------------------------------------------------------------------
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.producer_actor.actor_model

import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor
import kotlin.random.Random

fun main() {
    val actor = GlobalScope.actor<String> (
        onCompletion = CompletionHandler,
        capacity = 15
            ) {
        for (data in channel) {
            println(data)
        }
    }

    (1..15).forEach { _ ->
        actor.trySend(Random.nextInt(0, 30).toString())
    }

    actor.close()

    Thread.sleep(5_00L)
}

object CompletionHandler: CompletionHandler {
    override fun invoke(cause: Throwable?) {
        println("Completed!")
    }
}