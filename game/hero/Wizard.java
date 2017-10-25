package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.wizard.Deflect;
import game.spell.wizard.Drain;

public class Wizard extends Hero
{
    private static final int BASE_HP = 400;
    private static final int HP_PER_LV = 30;

    public Wizard(int x, int y, Game game)
    {
        super(x, y, HeroType.WIZARD, game);

        this.spells = new Spell[2];
        spells[0] = new Drain(this);
        spells[1] = new Deflect(this);
    }

    @Override
    public int getMaxHp()
    {
        return BASE_HP + HP_PER_LV * this.level;
    }

    public float getLandModifier()
    {
        if (this.tile == Tile.DESERT)
            return 1.1f;
        return 1f;
    }

    public float requestSpellModifier(Spell spell)
    {
        return spell.getModifier(this);
    }
}
