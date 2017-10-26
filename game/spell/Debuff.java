package game.spell;

import game.hero.Hero;

public final class Debuff {
    private Hero hero;

    private int duration;
    private int damage;
    private boolean stun;

    public Debuff(final Hero hero, final int damage, final boolean stun, final int duration) {
        this.hero = hero;
        this.damage = damage;
        this.stun = stun;
        this.duration = duration;
    }

    public void update() {
        duration--;
        hero.receiveDamage(damage);
    }

    public boolean getStun() {
        return stun;
    }

    public boolean isDone() {
        return (duration <= 0);
    }
}
