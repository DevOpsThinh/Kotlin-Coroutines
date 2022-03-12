/**
 * Kotlin Coroutines: Channels (A simple abstraction that u can use to transfer a stream of
 * values between coroutines).
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.channel.comparing

import com.forever.bee.channel.Fruit
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

// import kotlinx.coroutines.channels.produce
// import kotlin.random.Random

fun main() {
    val kChannel = Channel<String>()

    runBlocking {
        launch {
            for (i in Fruit.fruits) {
                val actualResult = kChannel.trySend(i)
                println(actualResult)
                //kChannel.trySend(i)
            }
            kChannel.close()
        }
        for (i in kChannel) {
            println("Received: $i")
        }
        println("Done!")
    }
}
//@OptIn(ExperimentalCoroutinesApi::class)
//fun main() {
//    val producer = GlobalScope.produce(capacity = 15) {
////        while (isActive) {
////            if (!isClosedForSend) {
////                    val number = Random.nextInt(0, 30)
////                if (trySend(number).isSuccess) {
////                    println("$number sent.")
////                } else {
////                    println("$number discarded.")
////                }
////            }
////        }
//        while (isActive) {
//
//            val number = Random.nextInt(0, 30)
//            send(number)
//            println("$number sent.")
//        }
//    }
//    Thread.sleep(150L)
//}
