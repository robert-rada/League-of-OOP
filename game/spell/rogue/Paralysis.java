package game.spell.rogue;

import game.Tile;
import game.hero.Hero;
import game.hero.Pyromancer;
import game.hero.Knight;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

public final class Paralysis extends Spell {
    private Hero hero;
    private static final int BASE_DAMAGE = 40;
    private static final int DAMAGE_PER_LEVEL = 10;

    private static final int DOT_BASE_DAMAGE = 40;
    private static final int DOT_DAMAGE_PER_LEVEL = 10;
    private static final int DOT_DURATION = 3;
    private static final int DOT_WOODS_DURATION = 6;

    private static final float ROGUE_MODIFIER = 0.9f;
    private static final float KNIGHT_MODIFIER = 0.8f;
    private static final float PYROMANCER_MODIFIER = 1.2f;
    private static final float WIZARD_MODIFIER = 1.25f;

    public Paralysis(final Hero hero) {
        super();
        this.hero = hero;
    }

    @Override
    public void postFightEffects(final Hero enemy, final float damageReceived) {
        float damage = DOT_BASE_DAMAGE + this.level * DOT_DAMAGE_PER_LEVEL;
        damage *= enemy.requestSpellModifier(this);
        damage *= this.hero.getLandModifier();

        int duration = DOT_DURATION;
        if (this.hero.getTile() == Tile.WOODS) {
            duration = DOT_WOODS_DURATION;
        }

        enemy.getDebuff(Math.round(damage), true, duration);
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
