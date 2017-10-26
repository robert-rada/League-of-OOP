package game;

import game.hero.Hero;
import game.hero.Pyromancer;
import game.hero.Knight;
import game.hero.Rogue;
import game.hero.Wizard;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public final class Game {
    private Scanner scanner;
    private PrintWriter writer;
    private final int mapWidth;
    private final int mapHeight;
    private Tile[][] map;
    private Hero[] heroList;

    public Game(final String inputName, final String outputName) {
        try {
            this.scanner = new Scanner(new FileReader(inputName));
            this.writer = new PrintWriter(outputName, "ASCII");
        } catch (IOException error) {
            System.err.println("Error opening input file " + error.getMessage());
        }

        this.mapHeight = this.scanner.nextInt();
        this.mapWidth = this.scanner.nextInt();

        this.initMap();
        this.initHeroes();
    }

    /**
     *
     */
    public void run() {
        int rounds = this.scanner.nextInt();

        this.scanner.nextLine(); // read new line char

        while (rounds-- > 0) {
            String input = this.scanner.nextLine();

            for (int i = 0; i < heroList.length; i++) {
                if (heroList[i].isDead()) {
                    continue;
                }

                switch (input.charAt(i)) {
                    case 'U':
                        heroList[i].move(-1, 0);
                        break;
                    case 'D':
                        heroList[i].move(1, 0);
                        break;
                    case 'R':
                        heroList[i].move(0, 1);
                        break;
                    case 'L':
                        heroList[i].move(0, -1);
                        break;
                    case '_':
                        heroList[i].move(0, 0);
                        break;
                    default:
                        throw new IllegalArgumentException("run(): invalid character");
                }
            }

            for (int i = 0; i < heroList.length; i++) {
                for (int j = i + 1; j < heroList.length; j++) {
                    if (heroList[i].getX() == heroList[j].getX()
                            && heroList[i].getY() == heroList[j].getY()
                            && !heroList[i].isDead()
                            && !heroList[j].isDead()) {
                        fight(heroList[i], heroList[j]);
                    }
                }
            }
        }

        printStatus();
    }

    private void fight(final Hero hero1, final Hero hero2) {
        hero1.preFightEffects(hero2);
        hero2.preFightEffects(hero1);

        int hero1TotalDamage = hero1.getTotalDamage(hero2);
        int hero2TotalDamage = hero2.getTotalDamage(hero1);

        hero1.receiveDamage(hero2TotalDamage);
        hero2.receiveDamage(hero1TotalDamage);

        hero1.postFightEffects(hero2, hero2.getRawDamage());
        hero2.postFightEffects(hero1, hero1.getRawDamage());

        if (hero1.isDead()) {
            hero2.gainXp(hero1.getLevel());
        }
        if (hero2.isDead()) {
            hero1.gainXp(hero2.getLevel());
        }
    }

    private void printStatus() {
        for (Hero hero : heroList) {
            this.writer.println(hero.toString());
        }
        this.writer.println("");

        this.writer.close();
        this.scanner.close();
    }

    private void initHeroes() {
        int heroNumber = this.scanner.nextInt();
        this.heroList = new Hero[heroNumber];

        for (int i = 0; i < heroNumber; i++) {
            char type = this.scanner.next(".").charAt(0);
            int x = this.scanner.nextInt();
            int y = this.scanner.nextInt();

            switch (type) {
                case 'P':
                    heroList[i] = new Pyromancer(x, y, this);
                    break;
                case 'R':
                    heroList[i] = new Rogue(x, y, this);
                    break;
                case 'K':
                    heroList[i] = new Knight(x, y, this);
                    break;
                case 'W':
                    heroList[i] = new Wizard(x, y, this);
                    break;
                default:
                    throw new IllegalArgumentException("initHeroes(): invalid character");
            }

            heroList[i].move(0, 0);
        }
    }

    private void initMap() {
        this.map = new Tile[this.mapHeight][this.mapWidth];

        this.scanner.nextLine(); // ready new line char

        for (int lin = 0; lin < this.mapHeight; lin++) {
            String inputLine = this.scanner.nextLine();

            for (int col = 0; col < this.mapWidth; col++) {
                switch (inputLine.charAt(col)) {
                    case 'L':
                        this.map[lin][col] = Tile.LAND;
                        break;
                    case 'V':
                        this.map[lin][col] = Tile.VOLCANIC;
                        break;
                    case 'D':
                        this.map[lin][col] = Tile.DESERT;
                        break;
                    case 'W':
                        this.map[lin][col] = Tile.WOODS;
                        break;
                    default:
                        throw new IllegalArgumentException("initMap(): invalid character");
                }
            }
        }
    }

    public Tile getTile(final int x, final int y) {
        return this.map[x][y];
    }
}
