/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.SpecieController;
import model.entities.Specie;

/**
 *
 * @author Adrian Vazquez
 */
public class SpecieModel extends AbstractModel<SpecieController, Specie, String>{

    public SpecieModel() {
        super(Specie.class);
    }
   
    
    
}
