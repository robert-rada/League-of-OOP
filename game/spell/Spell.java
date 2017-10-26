package game.spell;

import game.hero.Hero;
import game.hero.Wizard;
import game.hero.Rogue;
import game.hero.Knight;
import game.hero.Pyromancer;

public class Spell {
    private static final int BASE_DAMAGE = 0;
    private static final int DAMAGE_PER_LEVEL = 0;

    protected int level;

    public Spell() {
        this.level = 0;
    }

    /**
     * @return
     */
    public float getDamage() {
        return getBaseDamage() + this.level * getDamagePerLevel();
    }

    /**
     * @return
     */
    public void levelUp() {
        this.level++;
    }

    public void preFightEffects(final Hero enemy) {
    }

    public void postFightEffects(final Hero enemy, final float damageReceived) {
    }

    /**
     * @return
     */
    public float getModifier(final Hero hero) {
        return 1f;
    }

    /**
     * @return
     */
    public float getModifier(final Rogue enemy) {
        return 1f;
    }

    /**
     * @return
     */
    public float getModifier(final Knight enemy) {
        return 1f;
    }

    /**
     * @return
     */
    public float getModifier(final Pyromancer enemy) {
        return 1f;
    }

    /**
     * @return
     */
    public float getModifier(final Wizard enemy) {
        return 1f;
    }

    /**
     * @return
     */
    protected int getBaseDamage() {
        return BASE_DAMAGE;
    }

    /**
     * @return
     */
    protected int getDamagePerLevel() {
        return DAMAGE_PER_LEVEL;
    }
}
