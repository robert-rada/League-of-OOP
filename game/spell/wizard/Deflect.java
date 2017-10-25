package game.spell.wizard;

import game.hero.*;
import game.spell.Spell;

public class Deflect extends Spell
{
    private Hero hero;
    private static final float BASE_DAMAGE = 0.35f;
    private static final float DAMAGE_PER_LEVEL = 0.02f;
    private static final float MAX_DAMAGE = 0.7f;

    public Deflect(Hero hero)
    {
        this.hero = hero;
    }

    @Override
    public void postFightEffects(Hero enemy, float damage_received)
    {
        float percentage = (BASE_DAMAGE + this.level * DAMAGE_PER_LEVEL);
        if (percentage > MAX_DAMAGE)
            percentage = MAX_DAMAGE;

        float damage = damage_received;
        float class_modifier = enemy.requestSpellModifier(this);
        damage *= class_modifier * percentage * this.hero.getLandModifier();

        enemy.receiveDamage(Math.round(damage));
    }

    @Override
    public float getDamage()
    {
        return 0;
    }

    @Override
    public float getModifier(Rogue enemy)
    {
        return 1.2f;
    }

    @Override
    public float getModifier(Knight enemy)
    {
        return 1.4f;
    }

    @Override
    public float getModifier(Pyromancer enemy)
    {
        return 1.3f;
    }

    @Override
    public float getModifier(Wizard enemy)
    {
        return 0;
    }
}
