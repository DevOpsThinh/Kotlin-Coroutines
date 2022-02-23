package com.forever.bee.usecase.high_rise_building

import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val firstDefer = HighRiseBuilding().startProject("Traditional House", 15)
        val secondDefer = HighRiseBuilding().startProject("Smart House", 14)
        listOf(firstDefer, secondDefer).awaitAll()
    }
}