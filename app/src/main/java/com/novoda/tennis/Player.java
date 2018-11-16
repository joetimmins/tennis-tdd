package com.novoda.tennis;

class Player {

    private final String name;

    Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    void scoresPoint() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;

        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
