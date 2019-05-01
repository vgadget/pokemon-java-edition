package model.entities;

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

    public Specie(String name, Type type, Type secondaryType, Sprite front, Sprite back ,float height, float weight, int pokedexID,
            int maxHP, int minHP, int maxAttack, int minAttack,
            int maxSpecialAttack, int minSpecialAttack, int maxDefense,
            int minDefense, int maxSpecialDefense, int minSpecialDefense,
            int maxSpeed, int minSpeed, float precision, float evasion)
            throws Exception {

        if (validateFields(name, type, secondaryType, front, back ,height, weight, pokedexID,
                maxHP, minHP, maxAttack, minAttack,
                maxSpecialAttack, minSpecialAttack, maxDefense,
                minDefense, maxSpecialDefense, minSpecialDefense,
                maxSpeed, minSpeed, precision, evasion)) {

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
        } else {
            throw new Exception("INVALID FIELDS.");
        }
    }

    private boolean validateFields(String name, Type type, Type secondaryType, Sprite front, Sprite back ,float height, float weight, int pokedexID,
            int maxHP, int minHP, int maxAttack, int minAttack,
            int maxSpecialAttack, int minSpecialAttack, int maxDefense,
            int minDefense, int maxSpecialDefense, int minSpecialDefense,
            int maxSpeed, int minSpeed, float precision, float evasion) {

        if (name == null || name.equals("")) {
            return false;
        } else if (front == null || back == null) {
            return false;
        } else if (maxHP < minHP || Math.min(maxHP, minHP) <= 0) {
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
        } else if (evasion <= 0 || evasion > 1) {
            return false;
        } else if (precision <= 0 || evasion > 1) {
            return false;
        } else if (type == null) {
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public Sprite getFrontSprite() {
        return frontSprite;
    }

    public Sprite getBackSprite() {
        return backSprite;
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
        return name;
    }

    public Type getType() {
        return type;
    }

    public Type getSecondaryType() {
        return secondaryType;
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof Specie) {

            if (this.getType().compareTo(((Specie) o).getType()) == 0) {
                return this.getName().compareTo(((Specie) o).getName());
            } else {
                return this.getType().compareTo(((Specie) o).getType());
            }
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
