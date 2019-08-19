package model.entities;

import model.entities.movements.Move;
import java.util.LinkedList;
import java.util.List;
import languajes.StringResourceMultilingualManager;
import model.MoveModel;
import utilities.sound.Sound;

/**
 *
 * @author Adrian Vazquez
 */
public class Specie implements Entity<String> {

    private String name;
    private Float height;
    private Float weight;
    private Integer pokedexID;
    private Integer maxHP;
    private Integer minHP;
    private Integer maxAttack;
    private Integer minAttack;
    private Integer maxSpecialAttack;
    private Integer minSpecialAttack;
    private Integer maxDefense;
    private Integer minDefense;
    private Integer maxSpecialDefense;
    private Integer minSpecialDefense;
    private Integer maxSpeed;
    private Integer minSpeed;
    private Float precision;
    private Float evasion;
    private Sprite frontSprite;
    private Sprite backSprite;
    private Type type;
    private Type secondaryType;
    private String description;
    private Sound cry;
    private List<Move> movementsCanLearn;

    public Specie(String name, Type type, Type secondaryType, Sprite front, Sprite back, Float height, Float weight, Integer pokedexID,
            Integer maxHP, Integer minHP, Integer maxAttack, Integer minAttack,
            Integer maxSpecialAttack, Integer minSpecialAttack, Integer maxDefense,
            Integer minDefense, Integer maxSpecialDefense, Integer minSpecialDefense,
            Integer maxSpeed, Integer minSpeed, Float precision, Float evasion, String description, Sound cry)
            throws Exception {

        movementsCanLearn = new LinkedList<>();

        if (validateFields(name, type, secondaryType, front, back, height, weight, pokedexID,
                maxHP, minHP, maxAttack, minAttack,
                maxSpecialAttack, minSpecialAttack, maxDefense,
                minDefense, maxSpecialDefense, minSpecialDefense,
                maxSpeed, minSpeed, precision, evasion, description, cry)) {

            this.name = name;
            this.frontSprite = front;
            this.backSprite = back;
            this.height = height;
            this.weight = weight;
            this.pokedexID = pokedexID;
            this.minHP = minHP;
            this.maxHP = maxHP;
            this.maxAttack = maxAttack;
            this.minAttack = minAttack;
            this.maxSpecialAttack = maxSpecialAttack;
            this.minSpecialAttack = minSpecialAttack;
            this.maxDefense = maxDefense;
            this.minDefense = minDefense;
            this.maxSpecialDefense = maxSpecialDefense;
            this.minSpecialDefense = minSpecialDefense;
            this.maxSpeed = maxSpeed;
            this.minSpeed = minSpeed;
            this.precision = precision;
            this.evasion = evasion;
            this.type = type;
            this.secondaryType = secondaryType;
            this.description = description;
            this.cry = cry;
        } else {
            throw new Exception("INVALID FIELDS.");
        }
    }

    private boolean validateFields(String name, Type type, Type secondaryType, Sprite front, Sprite back, Float height, Float weight, Integer pokedexID,
            Integer maxHP, Integer minHP, Integer maxAttack, Integer minAttack,
            Integer maxSpecialAttack, Integer minSpecialAttack, Integer maxDefense,
            Integer minDefense, Integer maxSpecialDefense, Integer minSpecialDefense,
            Integer maxSpeed, Integer minSpeed, Float precision, Float evasion, String description, Sound cry) {

        if (name == null || name.equals("")) {
            return false;
        } else if (front == null || back == null) {
            return false;
        } else if (maxHP < minHP || Math.min(maxHP, minHP) <= 0 || 999 <= Math.max(minHP, maxHP)) {
            return false;
        } else if (maxAttack < minAttack || Math.min(maxAttack, minAttack) <= 0) {
            return false;
        } else if (height <= 0) {
            return false;
        } else if (weight <= 0) {
            return false;
        } else if (pokedexID <= 0) {
            return false;
        } else if (maxSpecialAttack < minSpecialAttack || Math.min(maxSpecialAttack, minSpecialAttack) <= 0) {
            return false;
        } else if (maxDefense < minDefense || Math.min(maxDefense, minDefense) <= 0) {
            return false;
        } else if (maxSpecialDefense < minSpecialDefense || Math.min(maxSpecialDefense, minSpecialDefense) <= 0) {
            return false;
        } else if (maxSpeed < minSpeed || Math.min(maxSpeed, minSpeed) <= 0) {
            return false;
        } else if (evasion <= 0 || evasion > 6) {
            return false;
        } else if (precision < 0.3 || precision > 1) {
            return false;
        } else if (type == null) {
            return false;
        } else if (description == null || description.equals("")) {
            return false;
        } else if (cry == null) {
            return false;
        }

        return true;
    }

    public String getName() {

        String key = this.getClass().getCanonicalName() + "-" + getPK() + "-getName()";

        if (StringResourceMultilingualManager.getInstance().keyExist(key)) {
            return StringResourceMultilingualManager.getInstance().getResource(key);
        } else {
            StringResourceMultilingualManager.getInstance().addKey(key);
            StringResourceMultilingualManager.getInstance().setResource(key, name);
        }

        return name;
    }

    public Sprite getFrontSprite() {
        return frontSprite;
    }

    public Sprite getBackSprite() {
        return backSprite;
    }

    public Sound getCry() {
        return cry;
    }

    public Float getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    public Integer getPokedexID() {
        return pokedexID;
    }

    public Integer getMaxHP() {
        return maxHP;
    }

    public Integer getMinHP() {
        return minHP;
    }

    public Integer getMaxAttack() {
        return maxAttack;
    }

    public Integer getMinAttack() {
        return minAttack;
    }

    public Integer getMaxSpecialAttack() {
        return maxSpecialAttack;
    }

    public Integer getMinSpecialAttack() {
        return minSpecialAttack;
    }

    public Integer getMaxDefense() {
        return maxDefense;
    }

    public Integer getMinDefense() {
        return minDefense;
    }

    public Integer getMaxSpecialDefense() {
        return maxSpecialDefense;
    }

    public Integer getMinSpecialDefense() {
        return minSpecialDefense;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public Integer getMinSpeed() {
        return minSpeed;
    }

    public Float getPrecision() {
        return precision;
    }

    public Float getEvasion() {
        return evasion;
    }

    @Override
    public String getPK() {
        return pokedexID + "_" + name;
    }

    public Type getType() {
        return type;
    }

    public Type getSecondaryType() {
        return secondaryType;
    }

    public String getDescription() {

        String key = this.getClass().getCanonicalName() + "-" + getPK() + "-getDescription()";

        if (StringResourceMultilingualManager.getInstance().keyExist(key)) {
            return StringResourceMultilingualManager.getInstance().getResource(key);
        } else {
            StringResourceMultilingualManager.getInstance().addKey(key);
            StringResourceMultilingualManager.getInstance().setResource(key, description);
        }

        return description;
    }

    public List<Move> getMovementsCanLearn() {

        if (this.movementsCanLearn.isEmpty()) {
            this.movementsCanLearn.add(MoveModel.getDefaultMove());
        }

        return movementsCanLearn;
    }

    public void setMovementsCanLearn(List<Move> movementsCanLearn) {

        this.movementsCanLearn.addAll(movementsCanLearn);

        if (this.movementsCanLearn.isEmpty()) {
            this.movementsCanLearn.add(MoveModel.getDefaultMove());
        }

    }

    @Override
    public String toString() {

        String secType = "";

        if (secondaryType != null) {
            secType = secondaryType.getName();
        }

        if (!StringResourceMultilingualManager.getInstance().keyExist("kilogram")) {
            StringResourceMultilingualManager.getInstance().addKey("kilogram");
        }

        if (!StringResourceMultilingualManager.getInstance().keyExist("meter")) {
            StringResourceMultilingualManager.getInstance().addKey("meter");
        }

        return name
                + ". I D :" + pokedexID + ". "
                + type.getName() + ". "
                + secType + ". "
                + getDescription() + ". "
                + getHeight() + " " + StringResourceMultilingualManager.getInstance().getResource("meter") + ". "
                + getWeight() + " " + StringResourceMultilingualManager.getInstance().getResource("kilogram") + ". ";
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof Specie) {

            return this.getPokedexID().compareTo(((Specie) o).getPokedexID());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
