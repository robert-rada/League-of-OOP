package game.hero;

import game.Game;
import game.Tile;
import game.spell.Spell;
import game.spell.Debuff;

import static java.lang.Integer.max;

public class Hero {
    private int x, y;
    protected int level;
    private int hp;
    private int xp;
    protected Game game;
    private Tile tile;
    protected Spell[] spells;
    private Debuff debuff;

    private static final int BASE_XP_GAIN = 200;
    private static final int XP_GAIN_PER_LEVEL = 40;

    private static final int BASE_XP = 250;
    private static final int XP_PER_LEVEL = 50;

    public Hero(final int x, final int y, final Game game) {
        this.x = x;
        this.y = y;

        this.level = 0;
        this.xp = 0;
        this.hp = this.getMaxHp();
        this.debuff = new Debuff(this, 0, false, 0);
        this.game = game;
    }

    /**
     * *
     *
     * @param target
     * @return
     */
    public int getTotalDamage(final Hero target) {
        int damage = 0;

        for (Spell spell : spells) {
            damage += Math.round(spell.getDamage()
                    * target.requestSpellModifier(spell)
                    * getLandModifier());
        }

        return damage;
    }

    /**
     *
     * @return
     */
    public int getRawDamage() {
        int damage = 0;

        for (Spell spell : spells) {
            damage += Math.round(spell.getDamage() * getLandModifier());
        }

        return damage;
    }

    /**
     *
     * @param dx
     * @param dy
     */
    public void move(final int dx, final int dy) {
        if (!debuff.isDone()) {
            debuff.update();

            if (this.isDead() || debuff.getStun()) {
                return;
            }
        }

        x += dx;
        y += dy;

        this.tile = game.getTile(this.x, this.y);
    }

    /**
     *
     * @param loserLevel
     */
    public void gainXp(final int loserLevel) {
        if (isDead()) {
            return;
        }

        xp += max(0, BASE_XP_GAIN - (this.level - loserLevel) * XP_GAIN_PER_LEVEL);

        while (xp >= getXpMax()) {
            for (Spell spell : spells) {
                spell.levelUp();
            }

            this.level++;
            this.hp = getMaxHp();
        }
    }

    /**
     *
     * @param damage
     */
    public void receiveDamage(final int damage) {
        this.hp -= damage;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return null;
    }

    /**
     *
     * @param classChar
     * @return
     */
    public String toString(final char classChar) {
        StringBuilder ret = new StringBuilder();

        ret.append(classChar);

        if (this.isDead()) {
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

    /**
     *
     * @param damage
     * @param stun
     * @param duration
     */
    public void getDebuff(final int damage, final boolean stun, final int duration) {
        this.debuff = new Debuff(this, damage, stun, duration);
    }

    /**
     *
     * @param target
     */
    public void preFightEffects(final Hero target) {
        for (Spell spell : spells) {
            spell.preFightEffects(target);
        }
    }

    /**
     *
     * @param target
     * @param damageReceived
     */
    public void postFightEffects(final Hero target, final float damageReceived) {
        for (Spell spell : spells) {
            spell.postFightEffects(target, damageReceived);
        }
    }

    /**
     *
     * @param spell
     * @return
     */
    public float requestSpellModifier(final Spell spell) {
        return 1f;
    }

    /**
     *
     * @return
     */
    public float getLandModifier() {
        return 1f;
    }

    /**
     *
     * @return
     */
    public boolean isDead() {
        return hp <= 0;
    }

    private int getXpMax() {
        return BASE_XP + level * XP_PER_LEVEL;
    }

    /**
     *
     * @return
     */
    public int getMaxHp() {
        return 0;
    }

    /**
     *
     * @return
     */
    public int getHp() {
        return this.hp;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return
     */
    public Tile getTile() {
        return this.tile;
    }
}
