/**
 * Kotlin Coroutines - Case study: An actor framework implementation.
 * References: https://www.manning.com/books/the-joy-of-kotlin
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
package com.forever.bee.case_study.actor_framework

import com.forever.bee.case_study.common.Result

/**
 * The [T] generic actor interface. It determines the behavior of an actor.
 * */
interface Actor<T> {
    companion object {
        /**
         * A generic helper function to provide a [Result].Empty with the [Result]<[Actor]>.
         * */
        fun <T> noSender(): Result<Actor<T>> = Result()
    }

    /** lets u access the actor context.*/
    val actorContext: ActorContext<T>
    /**
     * Returns a [Result] of this actor
     *
     * @return an instance of [Result] sealed class
     * */
    fun self(): Result<Actor<T>> = Result(this)
    /**
     * In order to simplify sending messages without having to indicate the [sender]
     *
     * @param message A generic [T] message
     * @param sender The sender argument default to [self] function
     * */
    fun tell(message: T, sender: Result<Actor<T>> = self())
    /**
     * Tells the actor that it should terminate itself.
     * */
    fun shutdown()
    /**
     * Sends a message with an [Actor] reference
     *
     * @param message A generic message
     * @param sender An [Actor] to reference
     * */
    fun tell(message: T, sender: Actor<T>) = tell(message, Result(sender))
}