/**
 * An example: The process of constructing a high-rise building.
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.usecase.high_rise_building

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class Builder (val name: String, var floors: Int = 0, private val scope: CoroutineScope) {

    private val random = Random

    suspend fun makeFoundation() = scope.launch {
        delay(350L)
        speakThroughBullhorn("[${Thread.currentThread().name}] The foundation is ready.")
    }

    suspend fun buildingFloor(floor: Int) = withContext(scope.coroutineContext) {
        delay(150L)

        if (random.nextBoolean()) {
            throw Exception("[${Thread.currentThread().name}] Something went wrong on the $floor'th floor")
        }

        speakThroughBullhorn("[${Thread.currentThread().name}] Floor number $floor is built.")
        floors++
    }

    suspend fun putTheWindows(floor: Int) = scope.launch {
        delay(100L)
        speakThroughBullhorn("[${Thread.currentThread().name}] Windows are placed on floor number $floor .")
    }

    suspend fun installDoors(floor: Int) = scope.launch {
        delay(100L)
        speakThroughBullhorn("[${Thread.currentThread().name}] Doors are installed on floor number $floor .")
    }

    suspend fun provideElectricity(floor: Int) = scope.launch {
        delay(100L)
        speakThroughBullhorn("[${Thread.currentThread().name}] Electricity is provided on floor number $floor")
    }

    suspend fun buildRoof() = scope.launch {
        delay(200L)
        speakThroughBullhorn("[${Thread.currentThread().name}] The roof is ready")
    }

    suspend fun fitOut(floor: Int) = scope.launch {
        delay(200L)
        speakThroughBullhorn("[${Thread.currentThread().name}] Floor number $floor is furnished")
    }

    fun speakThroughBullhorn(message: String) {
        println(message)
    }
}