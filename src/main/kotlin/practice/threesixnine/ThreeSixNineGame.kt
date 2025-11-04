package practice.threesixnine

class ThreeSixNineGame(
    private val name: String,
    private val users: List<User>,
    private val clapRule: ClapRule
)  {

    /**
     * 기본 369 게임 요구사항- number 에 3,6,9가 포함되면 "clap",
     * 아니면 입력받은 숫자를 String으로 리턴
     * 지역별 다른 규칙의 369 게임- 서울지역에서는 박수를 한번만 치지만,
     * 부산 지역에서는 3,6,9가 나온숫자만큼 박수를 쳐야한다.- 부산 do369(33) => "clapclap", 서울 do369(33) => "clap"
     */
    fun playGame() : Int {
        var failure = false
        var turn = 0
        while(!failure) {
            turn++
            val currentUser: User = users[(turn-1) % users.size]
            val shouldClap = turn.toString().contains(Regex("[369]"))
            val result = if(currentUser.processTurn(turn)) {
                if(shouldClap) clapRule.clap()
                else turn.toString()
            } else {
                failure = true
                "failure"
            }
            printLog("${currentUser.name} $result")
        }
        return turn
    }

    fun printLog(string: String) {
        println("[${this.name}] $string")
    }
}