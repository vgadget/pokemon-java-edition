/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import java.awt.Component;
import javax.swing.JLabel;

/**
 *
 * @author Adrian Vazquez
 */
public interface AidView {

    void getAudibleDescription();

    JLabel getInputDetector();

    boolean isEnabledAudioDescription();

    void remove(Component c);

    void requestFocus();

    void setEnableAudioDescriptions(boolean enableAudioDescriptions);
    
}
