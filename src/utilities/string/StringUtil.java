/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.string;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Adrian Vazquez
 */
public class StringUtil {

    public static float preferedFontSizeforLabel(Font f, String label, Dimension size) {
        float i = 1;

        int w = 0, h = 0;

        String t = label;

        FontMetrics fm;

        t = fixTextWitdth(t, f, size);

        while (w < size.getWidth() && h < size.getHeight()) {

            Font font = f.deriveFont(i);
            fm = getFontMetrics(font);
            i++;

            t = fixTextWitdth(t, f, size);

            w = fm.stringWidth(t);
            h = fm.getHeight();

        }

        return i;
    }

    public static float preferedFontSizeforText(Font f, String text, Dimension size) {
        float i = 1;

        int w = 0, h = 0;

        String t = text;

        FontMetrics fm;

        t = fixTextWitdth(t, f, size);

        while (h < size.getHeight()) {

            Font font = f.deriveFont(i);
            fm = getFontMetrics(font);
            i++;

            t = fixTextWitdth(t, f, size);

            w = fm.stringWidth(t);
            h = fm.getHeight() * (t.split("\n").length + 1);
        }

        return i;
    }

    public static String fixTextWitdth(String text, Font f, Dimension d) {

        String res = "";

        String buffer = "";
        for (int i = 0; i < text.length(); i++) {

            if (getFontMetrics(f).stringWidth(buffer) >= d.getWidth()) {
                res += buffer + "\n";
                buffer = "";
            } else {
                buffer += text.charAt(i);
            }

        }

        res += buffer;

        return res;
    }

    public static FontMetrics getFontMetrics(Font f) {

        JLabel j = new JLabel();
        j.setFont(f);
        FontMetrics fm = j.getFontMetrics(f);

        return fm;
    }

    public static List<String> divideText(String text, int size) {

        List<String> textDivided = new LinkedList<>();

        if (text.length() > size) {

            textDivided.addAll(divideText(text.substring(0, size), size));
            textDivided.addAll(divideText(text.substring(size, text.length()), size));

        } else {
            textDivided.add(text);
        }

        return textDivided;
    }

    public static String carousel(String text) {

        String s = "";

        if (text.length() > 0) {
            
            for (int i = 1; i < text.length(); i++) {

                s += text.charAt(i);
            }

            s += text.charAt(0);
        }
        return s;
    }

}
