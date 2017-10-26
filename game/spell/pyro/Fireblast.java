package game.spell.pyro;

import game.hero.Knight;
import game.hero.Pyromancer;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

public final class Fireblast extends Spell {
    private static final int BASE_DAMAGE = 350;
    private static final int DAMAGE_PER_LEVEL = 50;

    private static final float ROGUE_MODIFIER = 0.8f;
    private static final float KNIGHT_MODIFIER = 1.2f;
    private static final float PYROMANCER_MODIFIER = 0.9f;
    private static final float WIZARD_MODIFIER = 1.05f;

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
