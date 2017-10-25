package game.spell.pyro;

import game.hero.Knight;
import game.hero.Pyromancer;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

public class Fireblast extends Spell
{
    private static final int BASE_DAMAGE = 350;
    private static final int DAMAGE_PER_LEVEL = 50;

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
