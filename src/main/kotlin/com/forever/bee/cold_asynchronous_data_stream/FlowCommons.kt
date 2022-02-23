/**
 * Beyond coroutine Job - A Flow (Cold asynchronous data stream).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun flowingRiver(): Flow<Water> = flow {
    while (true) {
        delay(100)
        emit(Water())
    }
}

class Water {
    private val hydrogen = 2
    private val oxygen = 1

    override fun toString(): String = "H$hydrogen O$oxygen"
}