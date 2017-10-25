package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.pyro.Fireblast;
import game.spell.pyro.Ignite;

public class Pyromancer extends Hero
{
    private final static int BASE_HP = 500;
    private final static int HP_PER_LV = 50;

    public Pyromancer(int x, int y, Game game)
    {
        super(x, y, HeroType.PYROMANCER, game);

        spells = new Spell[2];
        spells[0] = new Fireblast();
        spells[1] = new Ignite(this);
    }

    @Override
    public int getMaxHp()
    {
        return BASE_HP + HP_PER_LV * this.level;
    }

    @Override
    public float getLandModifier()
    {
        if (this.tile == Tile.VOLCANIC)
            return 1.25f;
        return 1f;
    }

    public float requestSpellModifier(Spell spell)
    {
        return spell.getModifier(this);
    }
}
