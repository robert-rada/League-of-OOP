package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.rogue.Backstab;
import game.spell.rogue.Paralysis;

public final class Rogue extends Hero {
    private static final int BASE_HP = 600;
    private static final int HP_PER_LV = 40;
    private static final float LAND_MODIFIER = 1.15f;

    public Rogue(final int x, final int y, final Game game) {
        super(x, y, game);

        this.spells = new Spell[2];
        this.spells[0] = new Backstab(this);
        this.spells[1] = new Paralysis(this);
    }

    @Override
    public int getMaxHp() {
        return BASE_HP + HP_PER_LV * this.level;
    }

    public float getLandModifier() {
        if (this.getTile() == Tile.WOODS) {
            return LAND_MODIFIER;
        }
        return 1f;
    }

    public float requestSpellModifier(final Spell spell) {
        return spell.getModifier(this);
    }

    @Override
    public String toString() {
        return super.toString('R');
    }
}
