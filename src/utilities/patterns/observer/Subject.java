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
public interface Subject {

    public void attach(Observer observer);

    public void dettach(Observer observer);

    public void notifyObservers();

}
