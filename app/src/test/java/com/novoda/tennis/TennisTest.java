package com.novoda.tennis;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class TennisTest {

    private Game game;
    private Player roger;
    private Player rafa;

    @Before
    public void setUp() {
        roger = new Player("Roger");
        rafa = new Player("Rafa");

        game = new Game(roger, rafa);
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        String score = game.score();

        assertThat(score).isEqualTo("Love All");
    }

    @Test
    public void scoreShouldBeFifteenLoveAfterPlayerOneScoresAPoint() {
        roger.scoresPoint();

        String score = game.score();

        assertThat(score).isEqualTo("Fifteen - Love");
    }

    @Test
    public void scoreShouldBeThirtyLoveAfterPlayerOneScoresTwoPoints() {
        roger.scoresPoint();
        roger.scoresPoint();

        String score = game.score();

        assertThat(score).isEqualTo("Thirty - Love");
    }

    @Test
    public void scoreShouldBeThirtyAllAfterBothPlayersScoreTwoPoints() {
        roger.scoresPoint();
        roger.scoresPoint();

        rafa.scoresPoint();
        rafa.scoresPoint();

        String score = game.score();

        assertThat(score).isEqualTo("Thirty All");
    }
}
