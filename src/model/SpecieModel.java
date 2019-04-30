/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Controller;
import model.pokemon.Specie;

/**
 *
 * @author Adrian Vazquez
 */
public class SpecieModel extends Model<Controller, Specie, Comparable>{

    public SpecieModel(Controller controller) {
        super(controller, Specie.class);
    }
   
    
    
}
