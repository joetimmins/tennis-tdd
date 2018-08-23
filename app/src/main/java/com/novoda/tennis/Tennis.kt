package com.novoda.tennis

class Game(private val playerOne: Player, private val playerTwo: Player) {

    init {
        playerOne.otherPlayerIsOnForty = { playerTwo.currentScore == Point.FORTY }
        playerTwo.otherPlayerIsOnForty = { playerOne.currentScore == Point.FORTY }
    }

    fun score(): String {
        if (playerOne.currentScore == Point.GAME) return "${playerOne.name} wins!"
        if (playerTwo.currentScore == Point.GAME) return "${playerTwo.name} wins!"

        if (playerOne.currentScore == Point.FORTY && playerTwo.currentScore == Point.FORTY) return "Deuce"

        if (playerOne.currentScore == Point.ADVANTAGE) return "${Point.ADVANTAGE.asString} ${playerOne.name}"
        if (playerTwo.currentScore == Point.ADVANTAGE) return "${Point.ADVANTAGE.asString} ${playerTwo.name}"

        return "${playerOne.currentScore.asString} - ${playerTwo.currentScore.asString}"
    }
}

class Player constructor(val name: String) {
    var currentScore: Point = Point.LOVE
    lateinit var otherPlayerIsOnForty: () -> Boolean

    fun scoresPoint() {
        if (currentScore == Point.GAME) return
        currentScore = (if (currentScore == Point.FORTY && otherPlayerIsOnForty()) Point.ADVANTAGE else nextPoint())
    }

    private fun nextPoint(): Point {
        return if (currentScore == Point.FORTY) Point.GAME else Point.values()[currentScore.ordinal + 1]
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
