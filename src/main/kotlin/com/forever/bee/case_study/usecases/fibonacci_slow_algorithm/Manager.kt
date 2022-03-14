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

import com.forever.bee.case_study.actor_framework.AbstractActor
import com.forever.bee.case_study.actor_framework.Actor
import com.forever.bee.case_study.actor_framework.MessageProcessor
import com.forever.bee.case_study.common.Result
import com.forever.bee.case_study.common.List
import com.forever.bee.case_study.common.sequence

class Manager(
    id: String,
    list: List<Int>,
    /**
    * stores the preferences to its client, to which it will send the result of the computation.
    * */
    private val client: Actor<Result<List<Int>>>,
    /**
     * The number of workers to use is stored.
     * */
    private val workers: Int
) : AbstractActor<Int>(id) {
    /**
     * A list of pair of integers, holding both the number to process (.first) & the position in the list (.second).
     * */
    private val initial: List<Pair<Int, Int>>
    /**
     * The list of tasks remaining to be executed once all worker actors have been given their first task.
     * */
    private val workList: List<Int>
    /**
     * The list that holds the results of the computations.
     * */
    private val resultList: List<Int>
    /**
     * Is the heart of Manager, determining what it will be able to do.
     * This function is applied each time the manager receives a result from a worker.
     * */
    private val managerFunction: (Manager) -> (Behavior) -> (Int) -> Unit

    init {
        /**
         * The list of values to be processed is split at the number of workers in order to obtain a list of
         * initial tasks & a list of remaining tasks.
         * */
        val splitLists = list.splitAt(this.workers)
        /**
         * The list of initial tasks (numbers for which the Fibonacci value will be computed) is zipped with the
         * position of its element.
         * */
        this.initial = splitLists.first.zipWithPosition()
        /**
         * The workList is set to the remaining tasks.
         * */
        this.workList = splitLists.second
        /**
         * The resultList is initialized to an empty list.
         * */
        this.resultList = List()
        /*
        * Representing the work of the manager, is a curried function. Its behavior, & the received message(i), which
        * will be the result of a subtask.
        * */
        managerFunction = { manager ->
            { behavior ->
                { i ->
                    /*
                    * When a result is received, it's added to the list of result, which is fetched from the
                    * manager's behavior.
                    * */
                    val result = behavior.resultList.cons(i)
                     /*
                     * If (the resultList length = the input list length), the computation finishes, so the result
                     * is sent to the client. Otherwise, the become() function of the actorContext is called to change
                     * the behavior of the Manager. This change of behavior is a change of state. The new behavior is
                     * created with the tail of the workList & the current list of results.
                     * */
                    if (result.length == list.length) {
                        this.client.tell(Result(result))
                    } else {
                        manager.actorContext
                            .become(Behavior(behavior.workList
                                .tailSafe()
                                .getOrElse(List()), result))
                    }
                }
            }
        }
    }
    /**
     * This is the initial behavior of the Manager. As part of its initialization, it switches behavior, starting with
     * the workList containing the remaining tasks & the empty resultList.
     * */
    override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
        actorContext.become(Behavior(workList, resultList))
    }

    //                          The utility functions

    fun start() {
        /*
        * The Manager sends a message to itself. What the message is makes no difference, because the behavior has yet
        * to be initialized.
        * */
        onReceive(0, self())
        sequence(initial.map { this.initWorker(it) })
            .forEach(onSuccess = { this.initWorkers(it) }, // The workers are then created & initialized.
                onFailure = { this.tellClientEmptyResult(it.message ?: "Unknown error") })
    }
    /**
     * A function creates a function of type() -> Unit creating a [Worker] actor
     * */
    private fun initWorker(t: Pair<Int, Int>): Result<() -> Unit> =
        Result(a = { Worker("Worker " + t.second).tell(t.first, self()) })
    /**
     * A function executes the actor creation.
     * */
    private fun initWorkers(lst: List<() -> Unit>) {
        lst.forEach { it() }
    }
    /**
     * If there was an error, the client is informed.
     * */
    private fun tellClientEmptyResult(string: String) {
        client.tell(Result.failure("$string caused by empty input list."))
    }
    /**
     * A model class, that allows u to abstract the actor mutation.
     *
     * @param workList From which the head has been removed prior to calling the constructor
     * @param resultList To which a result has been added
     * */
    internal inner class Behavior
    internal constructor(
        internal val workList: List<Int>,
        internal val resultList: List<Int>
    ) : MessageProcessor<Int> {
        /**
         * Is called on reception of a message, first applies the [managerFunction] to the received message. Then it
         * sends the next task (the head of the [workList]) to the sender (a [Worker] actor that will process it) or,
         * if the [workList] is empty, it instructs the [Worker] actor to shut down.
         * */
        override fun process(message: Int, sender: Result<Actor<Int>>) {
            managerFunction(this@Manager)(this@Behavior)(message)
            sender.forEach(onSuccess = { a: Actor<Int> ->
                workList.headSafe().forEach({ a.tell(it, self()) }) { a.shutdown() }
            })
        }
    }

}