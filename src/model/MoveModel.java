package model;

import controller.Controller;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entities.movements.Move;
import model.entities.movements.Tackle;

/**
 *
 * @author Adrian Vazquez
 */
public class MoveModel extends AbstractModel<Controller, Move, Comparable> {

    public MoveModel() {
        super(Move.class);
    }

    @Override
    public List<Move> getAll() {

        List<Move> movements = new LinkedList<>();
        movements.add(getDefaultMove());

        movements.addAll(super.getAll());

        return movements;
    }

    public static Move getDefaultMove() {
       
        Move defaultMove = null;
        
        try {
            defaultMove = new Tackle();
        } catch (Exception ex) {
            Logger.getLogger(MoveModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return defaultMove;
    }
}
