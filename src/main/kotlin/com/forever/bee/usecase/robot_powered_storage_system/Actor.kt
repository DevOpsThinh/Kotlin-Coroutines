/**
 * Kotlin Coroutines:  Actor model
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
package com.forever.bee.usecase.robot_powered_storage_system

fun main() {

    val items = listOf(
        Parcel(1, "coffee"),
        Parcel(2, "chair"),
        Parcel(3, "sugar"),
        Parcel(4, "t-shirts"),
        Parcel(5, "pillowcases"),
        Parcel(6, "cellphones"),
        Parcel(7, "skateboard"),
        Parcel(8, "cactus plants"),
        Parcel(9, "lamps"),
        Parcel(10, "ice cream"),
        Parcel(11, "rubber duckies"),
        Parcel(12, "blankets"),
        Parcel(13, "glass"),
        Parcel(14, "coffee"),
        Parcel(15, "chair"),
        Parcel(16, "sugar"),
        Parcel(17, "t-shirts"),
        Parcel(18, "pillowcases"),
        Parcel(19, "cellphones"),
        Parcel(20, "skateboard"),
        Parcel(21, "cactus plants"),
        Parcel(21, "lamps"),
        Parcel(23, "ice cream"),
        Parcel(24, "rubber duckies"),
        Parcel(25, "blankets"),
    )

    val initialRobot = HelperRobot(1, items)

    initialRobot.organizePackages()
    Thread.sleep(5000)
}
