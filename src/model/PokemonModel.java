package model;

import controller.Controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Persistence;
import model.entities.Pokemon;
import utilities.DisplayMessage;

/**
 *
 * @author Adrian Vazquez
 */
public class PokemonModel extends EntityModelImpl<Controller, Pokemon, Comparable> {

    private Controller controller;

    public PokemonModel(Controller controller) {
        super(controller, Pokemon.class);
    }

    





}
