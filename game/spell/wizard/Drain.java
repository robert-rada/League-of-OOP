package game.spell.wizard;

import game.hero.Hero;
import game.hero.Pyromancer;
import game.hero.Knight;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

public final class Drain extends Spell {
    private Hero hero;
    private static final float BASE_DAMAGE = 0.2f;
    private static final float DAMAGE_PER_LEVEL = 0.05f;
    private static final float MIN_HP_MAX = 0.3f;

    private static final float ROGUE_MODIFIER = 0.8f;
    private static final float KNIGHT_MODIFIER = 1.2f;
    private static final float PYROMANCER_MODIFIER = 0.9f;
    private static final float WIZARD_MODIFIER = 1.05f;

    public Drain(final Hero hero) {
        this.hero = hero;
    }

    @Override
    public void postFightEffects(final Hero enemy, final float damageReceived) {
        float percentage = BASE_DAMAGE + this.level * DAMAGE_PER_LEVEL;
        float damage = Float.min(MIN_HP_MAX * enemy.getMaxHp(), enemy.getHp());
        float classModifier = enemy.requestSpellModifier(this);
        damage *= classModifier * percentage * this.hero.getLandModifier();

        enemy.receiveDamage(Math.round(damage));
    }

    @Override
    public float getDamage() {
        return 0;
    }

    @Override
    public float getModifier(final Rogue enemy) {
        return ROGUE_MODIFIER;
    }

    @Override
    public float getModifier(final Knight enemy) {
        return KNIGHT_MODIFIER;
    }

    @Override
    public float getModifier(final Pyromancer enemy) {
        return PYROMANCER_MODIFIER;
    }

    @Override
    public float getModifier(final Wizard enemy) {
        return WIZARD_MODIFIER;
    }
}
