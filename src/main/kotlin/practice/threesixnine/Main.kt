package practice.threesixnine

import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 1단계 : 주어진 요구사항에 맞게 369 게임 구현
 * 2단계 : 오답률에 따른 게임 종료 및 사용자 등 몇 가지 심화 기능과 클래스 추가
 * 3단계 : 지역별 다른 규칙의 369 게임을 위해 추상화 및 다형성 적용
 * 4단계 : 다양한 지역 동시 게임 진행을 위한 동시성 적용
 */
fun main() {
    val executorService: ExecutorService =  Executors.newFixedThreadPool(30)
    val users: List<User> = listOf(
        User("철수", 10.0),
        User("영희", 22.0),
        User("짱구", 5.2),
        User("맹구", 3.3)
    )
    val seoulGame = ThreeSixNineGame("seoul", users, DefaultClapRule())
    val busanGame = ThreeSixNineGame("busan", users, DoubleClapRule())

    try {
        val seoulFuture = executorService.submit<Int> { seoulGame.playGame() }
        val busanFuture = executorService.submit<Int> { busanGame.playGame() }
        val seoulTurn  = seoulFuture.get()
        val busanTurn = busanFuture.get()

        println("total = ${seoulTurn + busanTurn}")
    } catch (e : Exception) {
        e.printStackTrace()
    }
}