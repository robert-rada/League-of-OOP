package game.spell.wizard;

import game.hero.*;
import game.spell.Spell;

public class Drain extends Spell
{
    private Hero hero;
    private static final float BASE_DAMAGE = 0.2f;
    private static final float DAMAGE_PER_LEVEL = 0.05f;

    public Drain(Hero hero)
    {
        this.hero = hero;
    }

    @Override
    public void postFightEffects(Hero enemy, float damage_received)
    {
        float percentage = BASE_DAMAGE + this.level * DAMAGE_PER_LEVEL;
        float damage = Float.min(0.3f * enemy.getMaxHp(), enemy.getHp());
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
