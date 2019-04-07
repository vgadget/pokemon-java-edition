/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components.fonts;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author Adrian Vazquez
 */
public class PokemonFont {

    private Font createFont(int size) throws Exception{
        
        InputStream is = getClass().getResourceAsStream("POKEMON_FONT.ttf");
        Font f = Font.createFont(Font.TRUETYPE_FONT, is);
        f = f.deriveFont(0, size);

        return f;
    }
    
    public static Font getFont(int size) throws Exception{
        return new PokemonFont().createFont(size);
    }

}
