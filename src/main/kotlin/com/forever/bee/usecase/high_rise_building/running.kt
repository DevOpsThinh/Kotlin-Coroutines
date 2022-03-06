/**
 * An example: The process of constructing a high-rise building.
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
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