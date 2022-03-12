/**
 * Kotlin Coroutines:  Producer & Actors Section.
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.producer_actor.producer

import kotlinx.coroutines.*
// import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main() {
    //**********************************************
    //                      producer
    //**********************************************

    val producer = GlobalScope.produce(capacity = 15) {
//        while (isActive) {
//            if (!isClosedForSend) {
//                    val number = Random.nextInt(0, 30)
//                if (trySend(number).isSuccess) {
//                    println("$number sent.")
//                } else {
//                    println("$number discarded.")
//                }
//            }
//        }
        //-------------- Using blocking send & isActive
        while (isActive) {

            val number = Random.nextInt(0, 30)
            send(number)
            println("$number sent.")
        }
        // ------------- Using isClosedForReceive & tryReceive
    }

    //**********************************************
    //                      Consumer
    //**********************************************

//    GlobalScope.launch {
//        for (value in producer) {
//            println("$value received.")
//        }
//    }

//    while (!producer.isClosedForReceive) {
//        val number = producer.tryReceive()
//        println("$number received.")
//    }

//    GlobalScope.launch {
//        producer.consumeEach {
//            println("$it received.")
//        }
//    }

    GlobalScope.launch {
        while (isActive) {
            val value = producer.receive()
            println("$value received.")
        }
    }
    Thread.sleep(150L)
}

