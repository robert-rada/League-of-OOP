package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.Debuff;

import static java.lang.Integer.max;

public class Hero
{
    protected int x, y;
    protected int level;
    protected int hp;
    protected int xp;
    protected HeroType type;
    protected Game game;
    protected Tile tile;
    protected Spell[] spells;

    protected Debuff debuff;

    public Hero(int x, int y, HeroType type, Game game)
    {
        this.x = x;
        this.y = y;
        this.type = type;

        this.level = 0;
        this.xp = 0;
        this.hp = this.getMaxHp();
        this.debuff = new Debuff(this, 0, false, 0);
        this.game = game;
    }

    public int getTotalDamage(Hero target)
    {
        //float damage = 0f;
        int damage = 0;

        for (Spell spell : spells)
        {
            //damage += spell.getDamage() * target.requestSpellModifier(spell);
            damage += Math.round(spell.getDamage()
                    * target.requestSpellModifier(spell)
                    * getLandModifier());
        }

        //damage *= getLandModifier();

        //return Math.round(damage);
        return damage;
    }

    public int getRawDamage()
    {
        int damage = 0;

        for (Spell spell : spells)
        {
            damage += Math.round(spell.getDamage() * getLandModifier());
        }

        //return Math.round(damage);
        return damage;
    }

    public void move(int dx, int dy)
    {
        if (!debuff.isDone())
        {
            debuff.update();

            if (this.isDead() || debuff.getStun())
                return;
        }

        x += dx;
        y += dy;

        this.tile = game.getTile(this.x, this.y);
    }

    public void gainXp(int loser_level)
    {
        if (isDead())
            return;

        xp += max(0, 200 - (this.level - loser_level) * 40);

        while (xp >= getXpMax())
        {
            for (Spell spell : spells)
            {
                spell.levelUp();
            }

            this.level++;
            this.hp = getMaxHp();
        }
    }

    public void receiveDamage(int damage)
    {
        this.hp -= damage;
    }

    public String toString()
    {
        StringBuilder ret = new StringBuilder();

        switch(this.type)
        {
            case ROGUE:         ret.append("R ");
                break;
            case KNIGHT:        ret.append("K ");
                break;
            case PYROMANCER:    ret.append("P ");
                break;
            case WIZARD:        ret.append("W ");
        }

        if (this.isDead())
        {
            ret.append("dead");
            return ret.toString();
        }

        ret.append(this.level);
        ret.append(" ");
        ret.append(this.xp);
        ret.append(" ");
        ret.append(this.hp);
        ret.append(" ");
        ret.append(this.x);
        ret.append(" ");
        ret.append(this.y);

        return ret.toString();
    }

    protected float getHeroModifier(Hero target, float mod_rogue, float mod_knight,
                                    float mod_pyro, float mod_wizard)
    {
        switch(target.getType())
        {
            case ROGUE: return mod_rogue;
            case KNIGHT: return mod_knight;
            case WIZARD: return mod_wizard;
            case PYROMANCER: return mod_pyro;
        }

        return 1f;
    }

    public void getDebuff(int damage, boolean stun, int duration)
    {
        this.debuff = new Debuff(this, damage, stun, duration);
    }

    public void preFightEffects(Hero target)
    {
        for (Spell spell : spells)
        {
            spell.preFightEffects(target);
        }
    }

    public void postFightEffects(Hero target, float damage_received)
    {
        for (Spell spell : spells)
        {
            spell.postFightEffects(target, damage_received);
        }
    }

    public float requestSpellModifier(Spell spell)
    {
        return 1f;
    }

    public float getLandModifier()
    {
        return 1f;
    }

    public boolean isDead()
    {
        return hp <= 0;
    }

    private int getXpMax()
    {
        return 250 + level * 50;
    }

    public int getMaxHp()
    {
        return 0;
    }

    public int getHp()
    {
        return this.hp;
    }

    public HeroType getType()
    {
        return this.type;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getLevel()
    {
        return this.level;
    }

    public Tile getTile()
    {
        return this.tile;
    }
}
