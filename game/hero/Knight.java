package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.knight.Execute;
import game.spell.knight.Slam;

public final class Knight extends Hero {
    private static final int BASE_HP = 900;
    private static final int HP_PER_LV = 80;

    private static final float LAND_MODIFIER = 1.15f;

    public Knight(final int x, final int y, final Game game) {
        super(x, y, game);

        spells = new Spell[2];
        spells[0] = new Execute();
        spells[1] = new Slam();
    }

    @Override
    public int getMaxHp() {
        return BASE_HP + HP_PER_LV * this.level;
    }

    public float getLandModifier() {
        if (this.getTile() == Tile.LAND) {
            return LAND_MODIFIER;
        }

        return 1f;
    }

    public float requestSpellModifier(final Spell spell) {
        return spell.getModifier(this);
    }

    @Override
    public String toString() {
        return super.toString('K');
    }
}
