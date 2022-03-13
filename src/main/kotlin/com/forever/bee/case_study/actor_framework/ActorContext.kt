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

/**
 * The [T] generic Actor's context interface.
 * Acts as a way to access actors. This component will be minimalist & will be used primarily to access the actor state.
 * */
interface ActorContext<T> {
    /**
     * Allows access to the actor's behavior.
     * */
    fun behavior(): MessageProcessor<T>
    /**
     * Allows an actor to change its behavior by registering a new [MessageProcessor]
     * */
    fun become(behavior: MessageProcessor<T>)
}