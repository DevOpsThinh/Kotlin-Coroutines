/**
 * Kotlin Coroutines: ReactiveX vs Broadcast Channel
 * References:
 * https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-broadcast-channel/index.html
 * (This API is obsolete since 1.5.0. It will be deprecated with warning in 1.6.0 and with error in 1.7.0. It is replaced with SharedFlow.)
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.boardcast_channel

import io.reactivex.subjects.BehaviorSubject

fun main() {
    /**
     * An instance of type [BehaviorSubject] is created & called foodyDual.
     * */
    val foodyDual = BehaviorSubject.create<String>()
    // start producing items, publish them using onNext(item) function.
    foodyDual.apply {
        onNext(fruits[0])
        onNext(fruits[1])
        onNext(fruits[2])
    }
    // subscribe to items published from foodyDual using foodyDual.subscribe{}.
    foodyDual.subscribe {
        println("Consumer 1: $it")
    }
    foodyDual.subscribe {
        println("Consumer 2: $it")
    }

    foodyDual.apply {
        onNext(fruits[3])
        onNext(fruits[4])
    }
    // wait for a keystroke to exit the program.
    println("Press a key to exit.....")
    readLine()
    // signal completion of the subscription.
    foodyDual.onComplete()
}
