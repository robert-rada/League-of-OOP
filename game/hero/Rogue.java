package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.rogue.Backstab;
import game.spell.rogue.Paralysis;

public class Rogue extends Hero
{
    private static final int BASE_HP = 600;
    private static final int HP_PER_LV = 40;

    public Rogue(int x, int y, Game game)
    {
        super(x, y, HeroType.ROGUE, game);

        this.spells = new Spell[2];
        this.spells[0] = new Backstab(this);
        this.spells[1] = new Paralysis(this);
    }

    @Override
    public int getMaxHp()
    {
        return BASE_HP + HP_PER_LV * this.level;
    }

    public float getLandModifier()
    {
        if (this.tile == Tile.WOODS)
            return 1.15f;
        return 1f;
    }

    public float requestSpellModifier(Spell spell)
    {
        return spell.getModifier(this);
    }
}
