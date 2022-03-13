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
package com.forever.bee.usecases.robot_powered_storage_system

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

/**
 * A class which represents the entity Helper Robot.
 *
 * @param id the unique identifier of a Helper Robot
 * @param packages A set of [Parcel]
 * */
class HelperRobot(
    private val id: Int,
    private var packages: List<Parcel>
) {
    /**
     * Each robot has the same package capacity: 5
     * */
    companion object {
        private const val ROBOT_CAPACITY = 5
    }

    /**
     * Processing its items & if there are leftovers, it sends them to another helper robot.
     * */
    fun organizePackages() {
        val packagesToProcess = packages.take(ROBOT_CAPACITY)
        val leftOverItems = packages.drop(ROBOT_CAPACITY)

        packages = packagesToProcess

        val packageIds = packages.map {
            it.id
        }.fold("") { acc, item ->
            "$acc$item"
        }

//        if (!leftOverItems.isEmpty()) {
//            GlobalScope.launch {
//                val helperRobot = HelperRobot(id.inc(), leftOverItems)
//                helperRobot.organizePackages()
//            }
//        }

        // Run in parallel
        if (leftOverItems.isNotEmpty()) {
            GlobalScope.launch {
                val helperRobot = HelperRobot(id.inc(), leftOverItems)
                helperRobot.organizePackages()
            }
        }

        processPackages(packagesToProcess)

        println("Helper Robot #$id processed following packages: $packageIds")
    }

    /**
     * Each robot can process the items given to it.
     *
     * @param packagesToProcess A set of [Parcel]
     * */
    private fun processPackages(packagesToProcess: List<Parcel>) {
        val actor = GlobalScope.actor<Parcel>(capacity = ROBOT_CAPACITY) {

            var hasProcessedPackages = false

            while (packages.isNotEmpty()) {
                val currentPackage = receive()
//                val currentPackage = tryReceive() as Parcel

                currentPackage?.run {
                    organize(this)
                    packages -= currentPackage
                    hasProcessedPackages = true
                }

                if (hasProcessedPackages && currentPackage == null) {
                    cancel()
                }
            }
        }
        packagesToProcess.forEach { actor.trySend(it) }
    }
}
/**
 * A helper function to provide a result.
 *
 * @param packageItem A model [Parcel]
 * */
private fun organize(packageItem: Parcel) = println(
    "Organized package ${packageItem.id}:" + packageItem.name
)