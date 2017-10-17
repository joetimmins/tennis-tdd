package com.novoda.tennis;

import org.junit.Before;

public class TennisTest {

    @Before
    public void setUp() throws Exception {
        Player roger = new Player("Roger");
        Player rafa = new Player("Rafa");

        Game game = new Game(roger, rafa);

    }
}
