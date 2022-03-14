/**
 * Kotlin Coroutines - Use case: Fibonacci with a slow algorithm.
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
package com.forever.bee.case_study.usecases.fibonacci_slow_algorithm

import com.forever.bee.case_study.common.Result
import com.forever.bee.case_study.actor_framework.AbstractActor
import com.forever.bee.case_study.actor_framework.Actor

/**
 * A model class that represents the entity worker -> is stateless actor.
 * It computes the result & sends it back to the sender to which is has received a reference (another actor).
 *
 * @param id The unique identifier of the entity worker
 * */
class Worker(id: String): AbstractActor<Int>(id) {
    override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
        sender.forEach(onSuccess = {a: Actor<Int> ->
            /*
            * When the Worker receives a number, it reacts by computing the corresponding Fibonacci value and sending
            * it back to the caller
            * */
            a.tell(fibonacciSlowAlgorithm(message), self())
        })
    }
    private fun fibonacci(number: Int): Int {
        tailrec fun fibonacci(acc1: Int, acc2: Int, x: Int): Int = when (x) {
            0 -> 1
            1 -> acc1 + acc2
            else -> fibonacci(acc2, acc1 + acc2, x - 1)
        }
        return fibonacci(0, 1, number)
    }
    /**
     * Create long-lasting tasks with an inefficient algorithm.
     * */
    private fun fibonacciSlowAlgorithm(number: Int): Int {
        return when (number) {
            0 -> 1
            1 -> 1
            else -> fibonacciSlowAlgorithm(number - 1) + fibonacciSlowAlgorithm(number - 2)
        }
    }

}