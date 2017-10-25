package game.spell;

import game.hero.Hero;

public class Debuff
{
    private Hero hero;

    private int duration;
    private int damage;
    private boolean stun;

    public Debuff(Hero hero, int damage, boolean stun, int duration)
    {
        this.hero = hero;
        this.damage = damage;
        this.stun = stun;
        this.duration = duration;
    }

    public void update()
    {
        duration--;
        hero.receiveDamage(damage);
    }

    public boolean getStun()
    {
        return stun;
    }

    public boolean isDone()
    {
        return (duration <= 0);
    }
}
