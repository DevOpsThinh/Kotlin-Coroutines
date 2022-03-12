/**
 * Kotlin Coroutines: ReactiveX vs Broadcast Channel
 * References:
 * https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-broadcast-channel/index.html
 * (This API is obsolete since 1.5.0. It will be deprecated with warning in 1.6.0 and with error in 1.7.0. It is replaced with SharedFlow.)
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.broadcast_channel

import io.reactivex.subjects.PublishSubject

fun main() {
    /**
     * An instance of type [PublishSubject] is created & called foody.
     * */
    val foody = PublishSubject.create<String>()
    // start producing items, publish them using onNext(item) function.
    foody.apply {
        onNext(fruits[0])
        onNext(fruits[1])
        onNext(fruits[2])
    }
    // subscribe to items published from foody using foody.subscribe{}.
    foody.subscribe {
        println("Consumer 1: $it")
    }
    foody.subscribe {
        println("Consumer 2: $it")
    }

    foody.apply {
        onNext(fruits[3])
        onNext(fruits[4])
    }
    // wait for a keystroke to exit the program.
    println("Press a key to exit.....")
    readLine()
    // signal completion of the subscription.
    foody.onComplete()
}
