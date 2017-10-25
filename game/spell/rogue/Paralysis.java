package game.spell.rogue;

import game.Tile;
import game.hero.*;
import game.spell.Spell;

public class Paralysis extends Spell
{
    private Hero hero;
    private static final int BASE_DAMAGE = 40;
    private static final int DAMAGE_PER_LEVEL = 10;

    private static final int DOT_BASE_DAMAGE = 40;
    private static final int DOT_DAMAGE_PER_LEVEL = 10;
    private static final int DOT_DURATION = 3;
    private static final int DOT_WOODS_DURATION = 6;

    public Paralysis (Hero hero)
    {
        super();
        this.hero = hero;
    }

    @Override
    public void postFightEffects(Hero enemy, float damage_received)
    {
        float damage = DOT_BASE_DAMAGE + this.level * DOT_DAMAGE_PER_LEVEL;
        damage *= enemy.requestSpellModifier(this);
        damage *= this.hero.getLandModifier();

        int duration = DOT_DURATION;
        if (this.hero.getTile() == Tile.WOODS)
            duration = DOT_WOODS_DURATION;

        enemy.getDebuff(Math.round(damage), true, duration);
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
        return 0.9f;
    }

    @Override
    public float getModifier(Knight enemy)
    {
        return 0.8f;
    }

    @Override
    public float getModifier(Pyromancer enemy)
    {
        return 1.2f;
    }

    @Override
    public float getModifier(Wizard enemy)
    {
        return 1.25f;
    }
}
