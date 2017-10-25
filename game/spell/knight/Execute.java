package game.spell.knight;

import game.hero.*;
import game.spell.Spell;

import static java.lang.Float.min;

public class Execute extends Spell
{
    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_PER_LEVEL = 30;
    private static final float BASE_HP_LIMIT = 0.2f;
    private static final float HP_LIMIT_PER_LEVEL = 0.01f;
    private static final float MAX_HP_LIMIT = 0.4f;

    @Override
    public void preFightEffects(Hero enemy)
    {
        float hp_limit = BASE_HP_LIMIT + level * HP_LIMIT_PER_LEVEL;
        hp_limit = min(hp_limit, MAX_HP_LIMIT);

        if ((float)enemy.getHp() / enemy.getMaxHp() < hp_limit)
        {
            enemy.receiveDamage(enemy.getHp());
        }
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
        return 1.15f;
    }

    @Override
    public float getModifier(Knight enemy)
    {
        return 1f;
    }

    @Override
    public float getModifier(Pyromancer enemy)
    {
        return 1.1f;
    }

    @Override
    public float getModifier(Wizard enemy)
    {
        return 0.8f;
    }
}
