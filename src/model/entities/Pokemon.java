package model.entities;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import model.MoveModel;

/**
 *
 * @author Adrian Vazquez
 */
public class Pokemon implements Entity<String> {

    private String nickname;
    private Integer level;
    private Integer currentHp;
    private Integer maxHp;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private Float precission;
    private Float evasion;
    private Boolean canLeft;

    private Specie specie;
    private MoveSet moveSet;
    private Item item;
    private StatusCondition volatileStatusCondition;
    private StatusCondition persistentStatusCondition;

    private transient Boost boost;

    public Pokemon(String nickname, Integer level, Specie specie) throws Exception {

        setNickname(nickname);
        setLevel(level);
        setSpecie(specie);

        this.moveSet = new MoveSet();

        moveSet.add(MoveModel.getDefaultMove());

        generateRandomFIelds();

    }

    public Boost getBoost() {

        if (boost == null) {
            boost = new Boost();
        }

        return boost;
    }
    
    public void removeBoost(){
        boost = null;
    }

    public void generateRandomFIelds() {

        maxHp = ThreadLocalRandom.current().nextInt(specie.getMinHP(), specie.getMaxHP() + 1);
        currentHp = maxHp;
        attack = ThreadLocalRandom.current().nextInt(specie.getMinAttack(), specie.getMaxAttack() + 1);
        defense = ThreadLocalRandom.current().nextInt(specie.getMinDefense(), specie.getMaxDefense() + 1);
        specialAttack = ThreadLocalRandom.current().nextInt(specie.getMinSpecialAttack(), specie.getMaxSpecialAttack() + 1);
        specialDefense = ThreadLocalRandom.current().nextInt(specie.getMinSpecialDefense(), specie.getMaxSpecialDefense() + 1);
        speed = ThreadLocalRandom.current().nextInt(specie.getMinSpeed(), specie.getMaxSpeed() + 1);
        precission = specie.getPrecision();
        evasion = specie.getEvasion();
        canLeft = true;
    }

    public String getNickname() {

        if (this.nickname.isEmpty()) {
            return this.getSpecie().getName();
        }

        return nickname;
    }

    public void setNickname(String nickname) throws Exception {

        this.nickname = nickname.trim();
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

    public void setLevel(Integer level) {
        if (level >= 1 && level <= 100) {
            this.level = level;
        } else if (level < 1) {
            this.level = 1;
        } else {
            this.level = 100;
        }
    }

    public void setCurrentHp(Integer currentHp) {
        if (currentHp >= 0 && currentHp <= getMaxHp()) {
            this.currentHp = currentHp;
        } else if (currentHp < 0) {
            this.currentHp = 0;
        } else {
            this.currentHp = getMaxHp();
        }
    }

    public void setAttack(Integer attack) {
        if (attack > 0 && attack <= getSpecie().getMaxAttack()) {
            this.attack = attack;
        } else if (attack <= 0) {
            this.attack = 1;
        } else {
            attack = getSpecie().getMaxAttack();
        }
    }

    public void setDefense(Integer defense) {
        if (defense >= 0 && defense <= getSpecie().getMaxDefense()) {
            this.defense = defense;
        } else if (defense < 0) {
            this.defense = 0;
        } else {
            this.defense = getSpecie().getMaxDefense();
        }
    }

    public void setSpecialAttack(Integer specialAttack) {
        if (specialAttack > 0 && specialAttack <= getSpecie().getMaxSpecialAttack()) {
            this.specialAttack = specialAttack;
        } else if (specialAttack <= 0) {
            this.specialAttack = 1;
        } else {
            this.specialAttack = getSpecie().getMaxSpecialAttack();
        }
    }

    public void setSpecialDefense(Integer specialDefense) {
        if (specialDefense >= 0 && specialDefense <= getSpecie().getMaxSpecialDefense()) {
            this.specialDefense = specialDefense;
        } else if (specialDefense < 0) {
            this.specialDefense = 0;
        } else {
            this.specialDefense = getSpecie().getMaxSpecialDefense();
        }
    }

    public void setSpeed(Integer speed) {
        if (speed > 0 && speed <= getSpecie().getMaxSpeed()) {
            this.speed = speed;
        } else if (speed <= 0) {
            this.speed = 1;
        } else {
            this.speed = getSpecie().getMaxSpeed();
        }
    }

    public void setPrecission(Float precission) {
        if (precission >= 0 && precission <= getSpecie().getPrecision()) {
            this.precission = precission;
        } else if (precission < 0) {
            this.precission = 0f;
        } else {
            this.precission = getSpecie().getPrecision();
        }

    }

    public void setEvasion(Float evasion) {
        if (evasion >= 0 && evasion <= getSpecie().getEvasion()) {
            this.evasion = evasion;
        } else if (evasion < 0) {
            this.evasion = 0f;
        } else {
            this.evasion = getSpecie().getEvasion();
        }
    }

    public void setCanLeft(Boolean canLeft) {
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

    public Integer getLevel() {
        return level;
    }

    public Integer getCurrentHp() {
        return currentHp;
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public Integer getAttack() {
        return attack + getBoost().getAttackBoost();
    }

    public Integer getDefense() {
        return defense + getBoost().getDefenseBoost();
    }

    public Integer getSpecialAttack() {
        return specialAttack + getBoost().getSpecialAttackBoost();
    }

    public Integer getSpecialDefense() {
        return specialDefense + getBoost().getSpecialDefenseBoost();
    }

    public Integer getSpeed() {
        return speed + getBoost().getSpeedBoost();
    }

    public Float getPrecission() {
        return precission * getBoost().getPrecissionBoost();
    }

    public Float getEvasion() {
        return evasion + getBoost().getEvasionBoost();
    }

    public Boolean isCanLeft() {
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

class Boost {

    private Integer attackBoost;
    private Integer defenseBoost;
    private Integer specialAttackBoost;
    private Integer specialDefenseBoost;
    private Integer speedBoost;
    private Float precissionBoost;
    private Float evasionBoost;

    public Boost() {
        attackBoost
                = defenseBoost
                = specialAttackBoost
                = specialDefenseBoost
                = speedBoost = 0;
        evasionBoost = 0f;
        precissionBoost = 1f;

    }

    public Integer getAttackBoost() {
        return attackBoost;
    }

    public void setAttackBoost(Integer attackBoost) {
        this.attackBoost = attackBoost;
    }

    public Integer getDefenseBoost() {
        return defenseBoost;
    }

    public void setDefenseBoost(Integer defenseBoost) {
        this.defenseBoost = defenseBoost;
    }

    public Integer getSpecialAttackBoost() {
        return specialAttackBoost;
    }

    public void setSpecialAttackBoost(Integer specialAttackBoost) {
        this.specialAttackBoost = specialAttackBoost;
    }

    public Integer getSpecialDefenseBoost() {
        return specialDefenseBoost;
    }

    public void setSpecialDefenseBoost(Integer specialDefenseBoost) {
        this.specialDefenseBoost = specialDefenseBoost;
    }

    public Integer getSpeedBoost() {
        return speedBoost;
    }

    public void setSpeedBoost(Integer speedBoost) {
        this.speedBoost = speedBoost;
    }

    public Float getPrecissionBoost() {
        return precissionBoost;
    }

    public void setPrecissionBoost(Float precissionBoost) {
        this.precissionBoost = precissionBoost;
    }

    public Float getEvasionBoost() {
        return evasionBoost;
    }

    public void setEvasionBoost(Float evasionBoost) {
        this.evasionBoost = evasionBoost;
    }

}
