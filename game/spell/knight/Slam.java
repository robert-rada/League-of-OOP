package game.spell.knight;

import game.hero.*;
import game.spell.Spell;

public class Slam extends Spell
{
    private static final int BASE_DAMAGE = 100;
    private static final int DAMAGE_PER_LEVEL = 40;

    @Override
    public void postFightEffects(Hero enemy, float damage_received)
    {
        enemy.getDebuff(0, true, 1);
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
