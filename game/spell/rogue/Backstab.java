package game.spell.rogue;

import game.Tile;
import game.hero.*;
import game.spell.Spell;

public class Backstab extends Spell
{
    private Hero hero;
    private int counter;

    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_PER_LEVEL = 20;

    public Backstab(Hero hero)
    {
        this.hero = hero;
        this.counter = 0;
    }

    @Override
    public float getDamage()
    {
        float damage = super.getDamage();

        if (counter % 6 <= 1 && hero.getTile() == Tile.WOODS)
        {
            damage *= 1.5f;
        }

        counter++;

        return damage;
    }

    @Override
    protected int getBaseDamage()
    {
        return BASE_DAMAGE;
    }

    @Override
    protected int getDamagePerLevel()
    {
        return DAMAGE_PER_LEVEL;
    }

    @Override
    public float getModifier(Rogue enemy)
    {
        return 1.2f;
    }

    @Override
    public float getModifier(Knight enemy)
    {
        return 0.9f;
    }

    @Override
    public float getModifier(Pyromancer enemy)
    {
        return 1.25f;
    }

    @Override
    public float getModifier(Wizard enemy)
    {
        return 1.25f;
    }
}
