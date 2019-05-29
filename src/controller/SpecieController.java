package controller;

import java.util.List;
import model.SpecieModel;
import model.entities.Specie;
import model.entities.Sprite;
import model.entities.Type;
import utilities.sound.Sound;
import view.Pokedex.PokedexView;
import view.View;

/**
 *
 * @author Adrian Vazquez
 */
public class SpecieController extends AbstractController<SpecieModel, View, Specie> {

    /**
     *
     * @param data List -> String name, Type type, Type secondaryType, Sprite
     * front, Sprite back ,Float height, Float weight, Integer pokedexID,
     * Integer maxHP, Integer minHP, Integer maxAttack, Integer minAttack,
     * Integer maxSpecialAttack, Integer minSpecialAttack, Integer maxDefense,
     * Integer minDefense, Integer maxSpecialDefense, Integer minSpecialDefense,
     * Integer maxSpeed, Integer minSpeed, Float precision, Float evasion
     * 
     * @return Specie object
     */
    @Override
    public Specie createEntity(List<Object> data) {

        Specie specie = null;

        try {
            String name = (String) data.get(0);
            Type type = (Type) data.get(1);
            Type secondaryType = (Type) data.get(2);
            Sprite front = (Sprite) data.get(3);
            Sprite back = (Sprite) data.get(4);
            Float height = (Float) data.get(5);
            Float weight = (Float) data.get(6);
            Integer pokedexID = (Integer) data.get(7);
            Integer maxHP = (Integer) data.get(8);
            Integer minHP = (Integer) data.get(9);
            Integer maxAttack = (Integer) data.get(10);
            Integer minAttack = (Integer) data.get(11);
            Integer maxSpecialAttack = (Integer) data.get(12);
            Integer minSpecialAttack = (Integer) data.get(13);
            Integer maxDefense = (Integer) data.get(14);
            Integer minDefense = (Integer) data.get(15);
            Integer maxSpecialDefense = (Integer) data.get(16);
            Integer minSpecialDefense = (Integer) data.get(17);
            Integer maxSpeed = (Integer) data.get(18);
            Integer minSpeed = (Integer) data.get(19);
            Float precision = (Float) data.get(20);
            Float evasion = (Float) data.get(21);
            String description = (String) data.get(22);
            Sound cry = (Sound) data.get(23);

            specie = new Specie(name, type, secondaryType, front, back, height, weight, pokedexID, maxHP, minHP, maxAttack, minAttack, maxSpecialAttack, minSpecialAttack, maxDefense, minDefense, maxSpecialDefense, minSpecialDefense, maxSpeed, minSpeed, precision, evasion, description, cry);

        } catch (Exception e) {
            utilities.DisplayMessage.showErrorDialog("SPECIE CONTROLLER createEntity() INVALID FIELDS");
        }

        return specie;
    }

}
