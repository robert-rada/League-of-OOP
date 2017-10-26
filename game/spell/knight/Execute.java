package game.spell.knight;

import game.hero.Hero;
import game.hero.Pyromancer;
import game.hero.Knight;
import game.hero.Rogue;
import game.hero.Wizard;
import game.spell.Spell;

import static java.lang.Float.min;

public final class Execute extends Spell {
    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_PER_LEVEL = 30;
    private static final float BASE_HP_LIMIT = 0.2f;
    private static final float HP_LIMIT_PER_LEVEL = 0.01f;
    private static final float MAX_HP_LIMIT = 0.4f;

    private static final float ROGUE_MODIFIER = 1.15f;
    private static final float KNIGHT_MODIFIER = 1f;
    private static final float PYROMANCER_MODIFIER = 1.1f;
    private static final float WIZARD_MODIFIER = 0.8f;

    @Override
    public void preFightEffects(final Hero enemy) {
        float hpLimit = BASE_HP_LIMIT + level * HP_LIMIT_PER_LEVEL;
        hpLimit = min(hpLimit, MAX_HP_LIMIT);

        if ((float) enemy.getHp() / enemy.getMaxHp() < hpLimit) {
            enemy.receiveDamage(enemy.getHp());
        }
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
