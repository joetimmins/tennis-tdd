package com.novoda.tennis

import org.fest.assertions.api.Assertions.assertThat
import org.junit.Test

class TennisTest {

    private val roger = Player(name = "Roger")
    private val rafa = Player(name = "Rafa")
    private val game = Game(roger, rafa)

    @Test
    fun `initial score should be love all`() {
        assertThat(game.score()).isEqualTo("Love - Love")
    }

    @Test
    fun `score should be thirty all after both players score two points`() {
        roger.scoresPoint()
        roger.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()

        assertThat(game.score()).isEqualTo("Thirty - Thirty")
    }

    @Test
    fun `score should be forty love after first player scores three points`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        assertThat(game.score()).isEqualTo("Forty - Love")
    }

    @Test
    fun `when player one scores four consecutive points they win the game`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        assertThat(game.score()).isEqualTo("Roger wins!")
    }

    @Test
    fun `score goes to deuce when both players score three points`() {
        roger.scoresPoint()
        roger.scoresPoint()
        roger.scoresPoint()

        rafa.scoresPoint()
        rafa.scoresPoint()
        rafa.scoresPoint()

        assertThat(game.score()).isEqualTo("Deuce")
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

        assertThat(game.score()).isEqualTo("Advantage Roger")
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

        assertThat(game.score()).isEqualTo("Deuce")
    }
}
