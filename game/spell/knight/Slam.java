package game.spell.knight;

import game.hero.Hero;
import game.hero.Pyromancer;
import game.hero.Knight;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

public final class Slam extends Spell {
    private static final int BASE_DAMAGE = 100;
    private static final int DAMAGE_PER_LEVEL = 40;

    private static final float ROGUE_MODIFIER = 0.8f;
    private static final float KNIGHT_MODIFIER = 1.2f;
    private static final float PYROMANCER_MODIFIER = 0.9f;
    private static final float WIZARD_MODIFIER = 1.05f;

    @Override
    public void postFightEffects(final Hero enemy, final float damageReceived) {
        enemy.getDebuff(0, true, 1);
    }

    @Override
    protected int getBaseDamage() {
        return BASE_DAMAGE;
    }

    @Override
    protected int getDamagePerLevel() {
        return DAMAGE_PER_LEVEL;
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
