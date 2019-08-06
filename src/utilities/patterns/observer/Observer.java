/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.patterns.observer;

/**
 *
 * @author Adrian Vazquez
 * @param <S>
 */
public interface Observer <S extends Subject> {

    public void update();
}
