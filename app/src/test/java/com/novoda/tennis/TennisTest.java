package com.novoda.tennis;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class TennisTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        Player roger = new Player("Roger");
        Player rafa = new Player("Rafa");

        game = new Game(roger, rafa);
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        String score = game.score();

        assertThat(score).isEqualTo("Love - Love");
    }
}
