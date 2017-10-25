package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.knight.Execute;
import game.spell.knight.Slam;

public class Knight extends Hero
{
    private static final int BASE_HP = 900;
    private static final int HP_PER_LV = 80;

    public Knight(int x, int y, Game game)
    {
        super(x, y, HeroType.KNIGHT, game);

        spells = new Spell[2];
        spells[0] = new Execute();
        spells[1] = new Slam();
    }

    @Override
    public int getMaxHp()
    {
        return BASE_HP + HP_PER_LV * this.level;
    }

    public float getLandModifier()
    {
        if (this.tile == Tile.LAND)
            return 1.15f;
        return 1f;
    }

    public float requestSpellModifier(Spell spell)
    {
        return spell.getModifier(this);
    }
}
