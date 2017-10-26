package main;

import game.Game;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        Game game = new Game(args[0], args[1]);
        game.run();
    }
}
