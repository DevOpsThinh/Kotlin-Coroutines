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
import java.util.concurrent.Semaphore
// import kotlinx.coroutines.sync.Semaphore
// import kotlinx.coroutines.runBlocking

private val semaphore = Semaphore(1)

fun main() {
    // create an actor: Referee object.
    val referee = object : AbstractActor<Int>("Referee") {
        override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
            println("Game ended after $message shots.")
            semaphore.release() // A way to keep the app running until the game over.
        }
    }

    val player1st = Player("P001", "Ping", referee)
    val player2nd = player("P002", "Pong", referee)

    semaphore.acquire()

    player1st.tell(1, Result(player2nd))

    semaphore.acquire()
}

//fun main() = runBlocking {
//    // create an actor: Referee object.
//    val referee = object : AbstractActor<Int>("Referee") {
//        override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
//            println("Game ended after $message shots.")
//            semaphore.release() // A way to keep the app running until the game over.
//        }
//    }
//
//    val player1st = Player("P001", "Ping", referee)
//    val player2nd = player("P002", "Pong", referee)
//
//    semaphore.acquire()
//
//    player1st.tell(1, Result(player2nd))
//
//    semaphore.acquire()
//}
/**
 * A model that represents the entity player - The functional way.
 *
 * @param id The unique identifier of the entity player.
 * @param sound A message that's displayed by the players when they receive the ball.
 * @param referee An [Actor]<[Int]> that represents the referee object.
 * */
fun player(
    id: String,
    sound: String,
    referee: Actor<Int> ) = object : AbstractActor<Int>(id) {
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