package game.spell;

import game.hero.*;

public class Spell
{
    private static final int BASE_DAMAGE = 0;
    private static final int DAMAGE_PER_LEVEL = 0;

    protected int level;

    public Spell()
    {
        this.level = 0;
    }

    public float getDamage()
    {
        return getBaseDamage() + this.level * getDamagePerLevel();
    }

    public void levelUp()
    {
        this.level++;
    }

    public void preFightEffects(Hero enemy)
    {
    }

    public void postFightEffects(Hero enemy, float damage_received)
    {
    }

    public float getModifier(Hero hero)
    {
        return 1f;
    }

    public float getModifier(Rogue enemy)
    {
        return 1f;
    }

    public float getModifier(Knight enemy)
    {
        return 1f;
    }

    public float getModifier(Pyromancer enemy)
    {
        return 1f;
    }

    public float getModifier(Wizard enemy)
    {
        return 1f;
    }

    protected int getBaseDamage()
    {
        return BASE_DAMAGE;
    }

    protected int getDamagePerLevel()
    {
        return DAMAGE_PER_LEVEL;
    }
}
