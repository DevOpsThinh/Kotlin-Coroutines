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
package com.forever.bee.case_study.usecases.fibonacci_algorithms

import com.forever.bee.case_study.actor_framework.AbstractActor
import com.forever.bee.case_study.actor_framework.Actor
import com.forever.bee.case_study.common.List
import com.forever.bee.case_study.common.Result
import com.forever.bee.case_study.common.range
import java.util.concurrent.Semaphore

//                          The client application

private val semaphore = Semaphore(1) // wait for the actors to complete their work (prevent program shut down).
//private const val listLength = 20_000 // the number of tasks
private const val listLength = 500_000 // the number of tasks
private const val workers = 2
private val rnd = java.util.Random(0)
private val testList =
    range(0, listLength).map { rnd.nextInt(35) }

fun main() {
    semaphore.acquire() // starts the program.
    val startTime = System.currentTimeMillis()
    // An anonymous singleton object.
    val client =
        object : AbstractActor<Result<List<Int>>>("Client") {
            override fun onReceive(
                message: Result<List<Int>>,
                sender: Result<Actor<Result<List<Int>>>>
            ) {
                message.forEach({ processSuccess(it) },
                    { processFailure(it.message ?: "Unknown error") })
                println("Total time: " + (System.currentTimeMillis() - startTime))
                semaphore.release() // when the client receives the result.
            }
        }
    // The manager is instantiated & started.
    val manager =
        Manager("Manager", testList, client, workers)
    manager.start()
    semaphore.acquire() // wait for the job to finish.
}

private fun processFailure(message: String) {
    println(message)
}

fun processSuccess(lst: List<Int>) {
    println("Input: ${testList.splitAt(40).first}")
    println("Result: ${lst.splitAt(40).first}")
}