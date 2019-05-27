package model;

import controller.Controller;
import model.entities.Move;

/**
 *
 * @author Adrian Vazquez
 */
public class MoveModel extends AbstractModel<Controller, Move, Comparable> {

    public MoveModel(Controller controller) {
        super(controller, Move.class);
    }

    public static Move getDefaultMove() {

        return null;
    }
}
