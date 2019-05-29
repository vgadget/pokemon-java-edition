/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Pokedex;

import model.TypeModel;

/**
 *
 * @author Adrian Vazquez
 */
public class SecondaryTypeComboBoxModel extends TypeComboBoxModel{
    
    public SecondaryTypeComboBoxModel(TypeModel tm) {
        super(tm);
        getTypeList().add(null);
    }
    
}
