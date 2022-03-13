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

import com.forever.bee.case_study.Result
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.ThreadFactory

/**
 * A [T] generic abstract class contains all the stuff that's common to all actors. Will be extended by business actors.
 *
 * @param id the unique identifier of an [AbstractActor]<[T]>
 * */
abstract class AbstractActor<T>(protected val id: String) : Actor<T> {
    /**
     * Initializes the [ActorContext] property to a new [ActorContext].
     * */
    override val actorContext: ActorContext<T> = object : ActorContext<T> {
        /**
         * Delegates the default behavior to the [onReceive] function.
         * */
        var behavior: MessageProcessor<T> = object : MessageProcessor<T> {
            override fun process(message: T, sender: Result<Actor<T>>) {
                onReceive(message, sender)
            }
        }

        /**
         * To change its behavior, the [ActorContext] registers the new behavior.
         *
         * @param behavior A new [MessageProcessor]<[T]> behavior to be register
         * */
        @Synchronized
        override fun become(behavior: MessageProcessor<T>) {
            this.behavior = behavior
        }

        override fun behavior() = behavior
    }

    /**
     * Initializes the underlying [ExecutorService] to allow automatic shutdown when the main thread terminates.
     * */
    private val executor: ExecutorService = Executors.newSingleThreadExecutor(DaemonThreadFactory())

    /**
     * Holds the business processing, implemented by the user of the API.
     *
     * @param message The message was to hold.
     * @param sender A [Result]<[Actor]> sender
     * */
    abstract fun onReceive(message: T, sender: Result<Actor<T>>)

    override fun self(): Result<Actor<T>> {
        return Result(this)
    }

    override fun shutdown() {
        this.executor.shutdown()
    }

    /**
     * This function is how an actor receives a message. It's synchronized to ensure that messages are processed
     * one at a time.
     * */
    @Synchronized
    override fun tell(message: T, sender: Result<Actor<T>>) {
        executor.execute {
            try {
                // A received message is processed by the behavior returned by the actor context.
                actorContext.behavior().process(message, sender)
            } catch (e: RejectedExecutionException) {
                /*
                * This is probably normal & means all pending tasks where canceled because the actor was stopped.
                * */
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

    /**
     * An [DaemonThreadFactory] inner class that implemented the [ThreadFactory] interface.
     * */
    inner class DaemonThreadFactory : ThreadFactory {

        override fun newThread(runnableTask: Runnable): Thread {
            val thread = Executors.defaultThreadFactory().newThread(runnableTask)
            thread.isDaemon = true
            return thread
        }
    }
}