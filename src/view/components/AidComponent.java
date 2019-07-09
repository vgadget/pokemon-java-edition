/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

/**
 *
 * @author Adrian Vazquez
 */
public interface AidComponent {

    public String getDescription();

    public void press();

    public void setPressedVoiceFeedback(String text);
    
    public void getPressedVoiceFeedback();

}
