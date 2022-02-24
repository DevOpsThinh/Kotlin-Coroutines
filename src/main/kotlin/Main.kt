/**
 * Kotlin Coroutines: Creating coroutine using GlobalScope: app - program
 * @author Nguyen Truong Thinh
 * @since Kotlin 1.6 - Java 1.8 (Java 8)
 * Contact me: nguyentruongthinhvn2020@gmail.com || +84393280504
 * */
import kotlinx.coroutines.*

fun main() {
    orderMeal()
}

/*
* Creating simple coroutine using GlobalScope.launch function
* */
fun orderMeal() {
    println("Recived an order to prepare burger & diet coke")
    GlobalScope.launch {
        delay(2500)
        println("Preparing coke")
    }
    GlobalScope.launch {
        delay(3500)
        println("Preparing purger")
    }
    println("Next order please!")
    Thread.currentThread().join()
}
