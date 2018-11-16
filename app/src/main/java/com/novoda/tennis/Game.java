package com.novoda.tennis;

class Game {
    private final Player playerOne;
    private final Player playerTwo;

    Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    String score() {
        return "Love All";
    }
}
