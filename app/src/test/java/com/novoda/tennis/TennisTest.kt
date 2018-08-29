package com.novoda.tennis

import io.reactivex.observers.TestObserver
import org.fest.assertions.api.Assertions.assertThat
import org.junit.Test

class TennisTest {

    private val roger = Player("Roger")
    private val rafa = Player("Rafa")
    private val game = Game(roger, rafa)

    private val testObserver: TestObserver<String> = TestObserver()

    @Test
    fun `initial score should be love all`() {
        assertScore("Love - Love")
    }

    @Test
    fun `score should be thirty all after both players score two points`() {
        roger.scoresPoint()
        roger.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()

        assertScore("Thirty - Thirty")
    }

    @Test
    fun `score should be forty love after first player scores three points`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        assertScore("Forty - Love")
    }

    @Test
    fun `when player two scores four consecutive points they win the game`() {
        rafa.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()

        assertScore("Rafa wins!")
    }

    @Test
    fun `score goes to deuce when both players score three points`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        rafa.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()

        assertScore("Deuce")
    }

    @Test
    fun `when score is deuce, next player to score a point goes to advantage`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        rafa.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()

        roger.scoresPoint()

        assertScore("Advantage Roger")
    }

    @Test
    fun `when one player has an advantage, score goes back to deuce when the other player scores a point`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        rafa.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()

        roger.scoresPoint()
        rafa.scoresPoint()

        assertScore("Deuce")
    }

    @Test
    fun `epic thriller of a game finishes with player two scoring two consecutive points to win, after two deuces`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        rafa.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()
        // first deuce

        roger.scoresPoint()
        rafa.scoresPoint()
        // second deuce

        rafa.scoresPoint()
        rafa.scoresPoint()

        assertScore("Rafa wins!")
    }

    private fun assertScore(score: String) {
        assertThat(game.score()).isEqualTo(score)
    }
}
