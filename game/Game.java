package game;

import game.hero.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game
{
    private Scanner scanner;
    private PrintWriter writer;
    private final int map_width;
    private final int map_height;
    private Tile[][] map;
    private Hero[] hero_list;

    public Game(String input_name, String output_name)
    {
        try
        {
            this.scanner = new Scanner(new FileReader(input_name));
            this.writer = new PrintWriter(output_name, "ASCII");
        }
        catch(IOException error)
        {
            System.err.println("Error opening input file " + error.getMessage());
        }

        this.map_height = this.scanner.nextInt();
        this.map_width = this.scanner.nextInt();

        this.initMap();
        this.initHeroes();
    }

    public void run()
    {
        int rounds = this.scanner.nextInt();

       this.scanner.nextLine(); // read new line char

        while (rounds-- > 0)
        {
            String input = this.scanner.nextLine();

            for (int i = 0; i < hero_list.length; i++)
            {
                if (hero_list[i].isDead())
                    continue;

                switch(input.charAt(i))
                {
                    case 'U':   hero_list[i].move(-1, 0);
                                break;
                    case 'D':   hero_list[i].move(1, 0);
                                break;
                    case 'R':   hero_list[i].move(0, 1);
                                break;
                    case 'L':   hero_list[i].move(0, -1);
                                break;
                    case '_':   hero_list[i].move(0, 0);
                                break;
                    default:    throw new IllegalArgumentException("run(): invalid character");
                }
            }

            for (int i = 0; i < hero_list.length; i++)
                for (int j = i+1; j < hero_list.length; j++)
                    if (hero_list[i].getX() == hero_list[j].getX()
                            && hero_list[i].getY() == hero_list[j].getY()
                            && !hero_list[i].isDead()
                            && !hero_list[j].isDead())
                    {
                        if (i == 43 || j == 43)
                        {
                            System.out.println();
                        }
                        fight(hero_list[i], hero_list[j]);
                    }
        }

        printStatus();
    }

    private void fight(Hero hero1, Hero hero2)
    {
        hero1.preFightEffects(hero2);
        hero2.preFightEffects(hero1);

        /*
        if (hero1.isDead())
        {
            hero2.gainXp(hero1.getLevel());
            return;
        }
        if (hero2.isDead())
        {
            hero1.gainXp(hero2.getLevel());
            return;
        }
        */

        int hero1_damage = hero1.getTotalDamage(hero2);
        int hero2_damage = hero2.getTotalDamage(hero1);

        hero1.receiveDamage(hero2_damage);
        hero2.receiveDamage(hero1_damage);

        hero1.postFightEffects(hero2, hero2.getRawDamage());
        hero2.postFightEffects(hero1, hero1.getRawDamage());

        if (hero1.isDead())
            hero2.gainXp(hero1.getLevel());
        if (hero2.isDead())
            hero1.gainXp(hero2.getLevel());
    }

    private void printStatus()
    {
        for (Hero hero : hero_list)
        {
            this.writer.println(hero.toString());
        }
        this.writer.println("");

        this.writer.close();
        this.scanner.close();
    }

    private void initHeroes()
    {
        int hero_number = this.scanner.nextInt();
        this.hero_list = new Hero[hero_number];

        for (int i = 0; i < hero_number; i++)
        {
            char type = this.scanner.next(".").charAt(0);
            int x = this.scanner.nextInt();
            int y = this.scanner.nextInt();

            switch (type)
            {
                case 'P':   hero_list[i] = new Pyromancer(x, y, this);
                            break;
                case 'R':   hero_list[i] = new Rogue(x, y, this);
                            break;
                case 'K':   hero_list[i] = new Knight(x, y, this);
                            break;
                case 'W':   hero_list[i] = new Wizard(x, y, this);
                            break;
                default:    throw new IllegalArgumentException("initHeroes(): invalid character");
            }

            hero_list[i].move(0, 0);
        }
    }

    private void initMap()
    {
        this.map = new Tile[this.map_height][this.map_width];

        this.scanner.nextLine(); // ready new line char

        for (int lin = 0; lin < this.map_height; lin++)
        {
            String input_line = this.scanner.nextLine();

            for (int col = 0; col < this.map_width; col++)
            {
                switch (input_line.charAt(col))
                {
                    case 'L':   this.map[lin][col] = Tile.LAND;
                                break;
                    case 'V':   this.map[lin][col] = Tile.VOLCANIC;
                                break;
                    case 'D':   this.map[lin][col] = Tile.DESERT;
                                break;
                    case 'W':   this.map[lin][col] = Tile.WOODS;
                                break;
                    default:    throw new IllegalArgumentException("initMap(): invalid character");
                }
            }
        }
    }

    public Tile getTile(int x, int y)
    {
        return this.map[x][y];
    }
}
