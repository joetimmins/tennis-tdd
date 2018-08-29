package com.novoda.tennis

import io.reactivex.Observable

class Game(private val playerOne: Player, private val playerTwo: Player) {

    init {
        playerOne.otherPlayerScore = { playerTwo.currentScore }
        playerTwo.otherPlayerScore = { playerOne.currentScore }

        playerOne.setToDeuce = { playerTwo.currentScore = Point.FORTY }
        playerTwo.setToDeuce = { playerOne.currentScore = Point.FORTY }
    }

    fun score(): String {
        if (playerOne.currentScore == Point.GAME) return "${playerOne.name} wins!"
        if (playerTwo.currentScore == Point.GAME) return "${playerTwo.name} wins!"

        if (playerOne.currentScore == Point.FORTY && playerTwo.currentScore == Point.FORTY) return "Deuce"

        if (playerOne.currentScore == Point.ADVANTAGE) return "${Point.ADVANTAGE.asString} ${playerOne.name}"
        if (playerTwo.currentScore == Point.ADVANTAGE) return "${Point.ADVANTAGE.asString} ${playerTwo.name}"

        return "${playerOne.currentScore.asString} - ${playerTwo.currentScore.asString}"
    }

    fun scores(): Observable<String> = Observable.just("Love - Love")
}

class Player constructor(val name: String) {
    var currentScore: Point = Point.LOVE
    internal lateinit var otherPlayerScore: () -> Point
    internal lateinit var setToDeuce: () -> Unit

    fun scoresPoint() {
        when {
            currentScore == Point.GAME -> return
            currentScore != Point.FORTY -> currentScore = Point.values()[currentScore.ordinal + 1]
            currentScore == Point.FORTY -> currentScore = dealWithForty()
        }
    }

    private fun dealWithForty(): Point {
        // other player is on forty -> ADV
        // other player is on ADV -> i stay on forty, they go to forty
        // else -> GAME
        return when {
            otherPlayerScore() == Point.FORTY -> Point.ADVANTAGE
            otherPlayerScore() == Point.ADVANTAGE -> {
                setToDeuce()
                Point.FORTY
            }
            else -> Point.GAME
        }
    }

}

enum class Point(val asString: String) {
    LOVE("Love"),
    FIFTEEN("Fifteen"),
    THIRTY("Thirty"),
    FORTY("Forty"),
    ADVANTAGE("Advantage"),
    GAME("Game")
}
