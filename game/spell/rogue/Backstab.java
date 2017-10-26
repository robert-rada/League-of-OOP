package game.spell.rogue;

import game.Tile;
import game.hero.Hero;
import game.hero.Pyromancer;
import game.hero.Knight;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

public final class Backstab extends Spell {
    private Hero hero;
    private int counter;

    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_PER_LEVEL = 20;

    private static final float CRIT_BONUS = 1.5f;

    private static final float ROGUE_MODIFIER = 1.2f;
    private static final float KNIGHT_MODIFIER = 0.9f;
    private static final float PYROMANCER_MODIFIER = 1.25f;
    private static final float WIZARD_MODIFIER = 1.25f;

    public Backstab(final Hero hero) {
        this.hero = hero;
        this.counter = 0;
    }

    @Override
    public float getDamage() {
        float damage = super.getDamage();

        if (counter % 6 <= 1 && hero.getTile() == Tile.WOODS) {
            damage *= CRIT_BONUS;
        }

        counter++;

        return damage;
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
