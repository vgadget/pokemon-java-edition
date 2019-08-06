/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.patterns.observer;

/**
 *
 * @author Adrian Vazquez
 */
public interface Subject <O extends Observer>{

    public void attach(O observer);

    public void dettach(O observer);

    public void notifyObservers();

}
