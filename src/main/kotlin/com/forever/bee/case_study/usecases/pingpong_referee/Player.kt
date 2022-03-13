/**
 * Kotlin Coroutines - Use case: PingPongGame
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
package com.forever.bee.case_study.usecases.pingpong_referee

import com.forever.bee.case_study.Result
import com.forever.bee.case_study.actor_framework.AbstractActor
import com.forever.bee.case_study.actor_framework.Actor

/**
 * A model class that represents the entity player - The object way.
 *
 * @param id The unique identifier of the entity player.
 * @param sound A message that's displayed by the players when they receive the ball.
 * @param referee An [Actor]<[Int]> that represents the referee object.
 * */
class Player(
    id: String,
    private val sound: String,
    private val referee: Actor<Int>
) : AbstractActor<Int>(id) {
    override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
        /*
        * The business part of the actor, meaning the part that does what the user expects to see.
        * */
        println("$sound - $message")
        /*
        * If the number of balls is greater than 10 => game is over, gives the ball back to the referee.
        * Otherwise, sends it back to the other player if present.
        * */
        if (message >= 10) {
            referee.tell(message, sender)
        } else {
            sender.forEach({ actor: Actor<Int> ->
                actor.tell(message + 1, self())
            },
                /*
                * If the other player isn't present, registers an issue with the referee.
                * */
                { referee.tell(message, sender) })
        }
    }
}