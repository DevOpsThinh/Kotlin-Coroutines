/**
 * Beyond coroutine Job - A Flow (Cold asynchronous data stream).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

fun getNumber(): Flow<String> = flow{
    emit("1"); delay(500L)
    emit("2"); delay(500L)
    emit("3"); delay(500L)
}

fun getAlphabets(): Flow<String> = flow {
    emit("A"); delay(750L)
    emit("B"); delay(750L)
    emit("C"); delay(750L)
}

fun getAlphabetsLower(): Flow<String> = flow {
    emit("a"); delay(1000L)
    emit("b"); delay(1000L)
    emit("c"); delay(1000L)
}

fun downloadVideo(): Flow<Int> = flow {
    emit(20)
    delay(1000L)
    emit(40)
    delay(1000L)
    emit(60)
    delay(1000L)
    emit(80)
    delay(1000L)
    emit(100)
    delay(1000L)
}

fun getUnitValues(): Flow<String> = flow {
    emit("1 large")
    emit("1/ 2 cup")
    emit("1 cup")
    emit("6 cloves")
    emit("1")
    emit("1/ 4 teaspoon")
    emit("1.5 tablespoon")
    emit("1 tablespoon chopped")
    emit("as required")
    emit("1 cup")
}

fun getIngredients(): Flow<String> = flow {
    emit("Aubergine eggplant") // ca tim
    emit("Onion")
    emit("Tomatoes")
    emit("Garlic") // toi
    emit("Green Chili") // ot xanh
    emit("Red Chili Powder") // bot ot do
    emit("Oil")
    emit("Coriander Leaves") // rau mui
    emit("Salt")
    emit("Sugar")
    emit("Main Noodles")
}

fun getThreeIngredients(): Flow<String> = flow {
    delay(1000L)
    emit("salt")
    delay(1000L)
    emit("Pepper")
    delay(1000L)
    emit("Potato")
}

fun flowingRiver(): Flow<Water> = flow {
    while (true) {
        delay(100L)
        emit(Water())
    }
}

fun pollutionByWeThePeople() = AcidicWater(Random.nextInt(1, 10))

class Water {
    private val hydrogen = 2
    private val oxygen = 1

    override fun toString(): String = "H$hydrogen O$oxygen"
}

class AcidicWater(val pH: Int) {
    private val hydrogen = 2
    private val oxygen = 1

    override fun toString() = "H$hydrogen 0$oxygen pH=$pH"
}