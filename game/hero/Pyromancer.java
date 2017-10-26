package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.pyro.Fireblast;
import game.spell.pyro.Ignite;

public final class Pyromancer extends Hero {
    private static final int BASE_HP = 500;
    private static final int HP_PER_LV = 50;
    private static final float LAND_MODIFIER = 1.25f;

    public Pyromancer(final int x, final int y, final Game game) {
        super(x, y, game);

        spells = new Spell[2];
        spells[0] = new Fireblast();
        spells[1] = new Ignite(this);
    }

    @Override
    public int getMaxHp() {
        return BASE_HP + HP_PER_LV * this.level;
    }

    @Override
    public float getLandModifier() {
        if (this.getTile() == Tile.VOLCANIC) {
            return LAND_MODIFIER;
        }
        return 1f;
    }

    public float requestSpellModifier(final Spell spell) {
        return spell.getModifier(this);
    }

    @Override
    public String toString() {
        return super.toString('P');
    }
}
