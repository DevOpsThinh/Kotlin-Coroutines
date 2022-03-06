/**
 * Operators in Flow: combineTransform
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - JDK 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
package com.forever.bee.cold_asynchronous_data_stream.operators

// import kotlinx.coroutines.flow.combine
import com.forever.bee.cold_asynchronous_data_stream.getAlphabets
import com.forever.bee.cold_asynchronous_data_stream.getAlphabetsLower
import com.forever.bee.cold_asynchronous_data_stream.getNumber
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTakenTransform = measureTimeMillis {
        combineTransform(getNumber(), getAlphabets(), getAlphabetsLower()) {
            one, A, a ->
            emit("$one $A $a")
        }.collect {
            result ->
            println("Final result $result")
        }
    }
    println("===== Total time to execute with transform is $timeTakenTransform =====")
}

//fun main() = runBlocking {
//    val timeTaken = measureTimeMillis {
//        combine(getNumber(), getAlphabets(), getAlphabetsLower()) {
//            one, A, a ->
//            "$one $A $a"
//        }.collect{
//            result ->
//            println("Final result $result")
//        }
//    }
//    println("===== Total time to execute is $timeTaken =====")
//}

