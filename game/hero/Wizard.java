package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.wizard.Deflect;
import game.spell.wizard.Drain;

public final class Wizard extends Hero {
    private static final int BASE_HP = 400;
    private static final int HP_PER_LV = 30;
    private static final float LAND_MODIFIER = 1.1f;

    public Wizard(final int x, final int y, final Game game) {
        super(x, y, game);

        this.spells = new Spell[2];
        spells[0] = new Drain(this);
        spells[1] = new Deflect(this);
    }

    @Override
    public int getMaxHp() {
        return BASE_HP + HP_PER_LV * this.level;
    }

    @Override
    public float getLandModifier() {
        if (this.getTile() == Tile.DESERT) {
            return LAND_MODIFIER;
        }
        return 1f;
    }

    public float requestSpellModifier(final Spell spell) {
        return spell.getModifier(this);
    }

    @Override
    public String toString() {
        return super.toString('W');
    }
}
