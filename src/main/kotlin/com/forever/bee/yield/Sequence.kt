/**
 * Kotlin Coroutines: Coroutines with sequence & iterators
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.yield

fun main() {

    YieldCommons.lists
        .asSequence()
        .filter {
            print("filter, ")
            it > 0
        }
        .map { print("map, ") }
        .forEach { _ ->
            print("forEach, ")
        }
}