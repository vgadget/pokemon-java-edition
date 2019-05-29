package model;

import controller.Controller;
import model.entities.Move;

/**
 *
 * @author Adrian Vazquez
 */
public class MoveModel extends AbstractModel<Controller, Move, Comparable> {

    public MoveModel() {
        super(Move.class);
    }

    public static Move getDefaultMove() {

        return null;
    }
}
