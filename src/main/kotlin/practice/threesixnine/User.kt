package practice.threesixnine

import kotlin.random.Random


class User(
    val name: String,
    val failureRate: Double
) {
    fun processTurn(number: Number): Boolean {
        // 0 ~1  * 100 -> 0~100
        val random : Double = Random.nextDouble(100.0)
        return random >= failureRate
    }
}