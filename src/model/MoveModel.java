/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Controller;
import model.pokemon.Move;

/**
 *
 * @author Adrian Vazquez
 */
public class MoveModel extends Model<Controller, Move, Comparable>{
    
    public MoveModel(Controller controller) {
        super(controller, Move.class);
    }
    
}
