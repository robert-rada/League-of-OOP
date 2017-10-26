package game.spell.wizard;

import game.hero.Hero;
import game.hero.Pyromancer;
import game.hero.Knight;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

public final class Deflect extends Spell {
    private Hero hero;
    private static final float BASE_DAMAGE = 0.35f;
    private static final float DAMAGE_PER_LEVEL = 0.02f;
    private static final float MAX_DAMAGE = 0.7f;

    private static final float ROGUE_MODIFIER = 1.2f;
    private static final float KNIGHT_MODIFIER = 1.4f;
    private static final float PYROMANCER_MODIFIER = 1.3f;
    private static final float WIZARD_MODIFIER = 0;

    public Deflect(final Hero hero) {
        this.hero = hero;
    }

    @Override
    public void postFightEffects(final Hero enemy, final float damageReceived) {
        float percentage = (BASE_DAMAGE + this.level * DAMAGE_PER_LEVEL);
        if (percentage > MAX_DAMAGE) {
            percentage = MAX_DAMAGE;
        }

        float damage = damageReceived;
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
