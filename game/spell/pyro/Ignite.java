package game.spell.pyro;

import game.hero.*;
import game.spell.Spell;

public class Ignite extends Spell
{
    private Hero hero;
    private static final int BASE_DAMAGE = 150;
    private static final int DAMAGE_PER_LEVEL = 20;

    private static final int DOT_BASE_DAMAGE = 50;
    private static final int DOT_DAMAGE_PER_LEVEL = 30;

    public Ignite(Hero hero)
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
        enemy.getDebuff(Math.round(damage), false, 2);
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
        return 0.8f;
    }

    @Override
    public float getModifier(Knight enemy)
    {
        return 1.2f;
    }

    @Override
    public float getModifier(Pyromancer enemy)
    {
        return 0.9f;
    }

    @Override
    public float getModifier(Wizard enemy)
    {
        return 1.05f;
    }
}
