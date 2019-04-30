package model.pokemon;

import model.Entity;

/**
 *
 * @author Adrian Vazquez
 */
public class Pokemon implements Entity<String> {

    private String nickname;
    private int level;
    private int currentHp;
    private int maxHp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private float precission;
    private float evasion;
    private boolean canLeft;

    private Specie specie;
    private MoveSet moveSet;
    private Item item;
    private StatusCondition volatileStatusCondition;
    private StatusCondition persistentStatusCondition;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) throws Exception {
        if (nickname != null && !nickname.equals("")) {
            this.nickname = nickname;
        } else {
            throw new Exception("INVALID POKEMON NICKNAME: " + nickname);
        }
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) throws Exception {
        if (specie != null) {
            this.specie = specie;
        } else {
            throw new Exception("NULL SPECIE");
        }
    }

    public MoveSet getMoveSet() {
        return moveSet;
    }

    public void setMoveSet(MoveSet moveSet) throws NullPointerException {
        if (moveSet != null) {
            this.moveSet = moveSet;
        } else {
            throw new NullPointerException(Pokemon.class.getName());
        }
    }

    public void setLevel(int level) {
        if (level >= 1 && level <= 100) {
            this.level = level;
        } else if (level < 1) {
            this.level = 1;
        } else {
            this.level = 100;
        }
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp >= 0 && currentHp <= getMaxHp()) {
            this.currentHp = currentHp;
        } else if (currentHp < 0) {
            this.currentHp = 0;
        } else {
            this.currentHp = getMaxHp();
        }
    }

    public void setAttack(int attack) {
        if (attack > 0 && attack <= getSpecie().getMaxAttack()) {
            this.attack = attack;
        } else if (attack <= 0) {
            this.attack = 1;
        } else {
            attack = getSpecie().getMaxAttack();
        }
    }

    public void setDefense(int defense) {
        if (defense >= 0 && defense <= getSpecie().getMaxDefense()) {
            this.defense = defense;
        } else if (defense < 0) {
            this.defense = 0;
        } else {
            this.defense = getSpecie().getMaxDefense();
        }
    }

    public void setSpecialAttack(int specialAttack) {
        if (specialAttack > 0 && specialAttack <= getSpecie().getMaxSpecialAttack()) {
            this.specialAttack = specialAttack;
        } else if (specialAttack <= 0) {
            this.specialAttack = 1;
        } else {
            this.specialAttack = getSpecie().getMaxSpecialAttack();
        }
    }

    public void setSpecialDefense(int specialDefense) {
        if (specialDefense >= 0 && specialDefense <= getSpecie().getMaxSpecialDefense()) {
            this.specialDefense = specialDefense;
        } else if (specialDefense < 0) {
            this.specialDefense = 0;
        } else {
            this.specialDefense = getSpecie().getMaxSpecialDefense();
        }
    }

    public void setSpeed(int speed) {
        if (speed > 0 && speed <= getSpecie().getMaxSpeed()) {
            this.speed = speed;
        } else if (speed <= 0) {
            this.speed = 1;
        } else {
            this.speed = getSpecie().getMaxSpeed();
        }
    }

    public void setPrecission(float precission) {
        if (precission >= 0 && precission <= getSpecie().getPrecision()) {
            this.precission = precission;
        } else if (precission < 0) {
            this.precission = 0;
        } else {
            this.precission = getSpecie().getPrecision();
        }

    }

    public void setEvasion(float evasion) {
        if (evasion >= 0 && evasion <= getSpecie().getEvasion()) {
            this.evasion = evasion;
        } else if (evasion < 0) {
            this.evasion = 0;
        } else {
            this.evasion = getSpecie().getEvasion();
        }
    }

    public void setCanLeft(boolean canLeft) {
        this.canLeft = canLeft;
    }

    public void setVolatileStatusCondition(StatusCondition volatileStatusCondition) {
        this.volatileStatusCondition = volatileStatusCondition;
    }

    public void setPersistentStatusCondition(StatusCondition persistentStatusCondition) {
        this.persistentStatusCondition = persistentStatusCondition;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public float getPrecission() {
        return precission;
    }

    public float getEvasion() {
        return evasion;
    }

    public boolean isCanLeft() {
        return canLeft;
    }

    public StatusCondition getVolatileStatusCondition() {
        return volatileStatusCondition;
    }

    public StatusCondition getPersistentStatusCondition() {
        return persistentStatusCondition;
    }

    @Override
    public String getPK() {
        return this.getNickname();
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof Pokemon) {
            return this.getPK().compareTo((String) o.getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
