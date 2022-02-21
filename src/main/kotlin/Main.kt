import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
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

