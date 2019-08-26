package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import utilities.image.Dimensions;
import utilities.image.RGB;

/**
 *
 * @author Adrian Vazquez
 */
public class DialogPane {

    private static boolean option;

    public synchronized static boolean showYesOrNotDialog(String message) {

        Semaphore mutex = new Semaphore(1);

        if (message.length() > 220) {
            message = message.substring(0, 220) + "...";
        }

        option = false;
        JFrame frame = new JFrame();
        Dimension size = Dimensions.getSelectedResolution();
        size = new Dimension((int) (size.width / 2), (int) (size.height / 2));

        AidPanelImpl panel = new AidPanelImpl();
        panel.setLayout(null);
        Color backgroundColor = new Color(30, 30, 30);
        panel.setBackground(backgroundColor);

        Dimension buttonSize = new Dimension((int) (size.width / 1.5f), size.height / 3);
        BufferedImage background = utilities.image.ImageUtil.getEmptyImage(buttonSize);

        JButton yes, no;

        JTextArea textArea = new JTextArea(message);
        textArea.setSize((int) (size.width / 1.1f), (int) ((size.height - buttonSize.height) / 1.1f));
        textArea.setLocation((int) (size.width - textArea.getSize().width) / 2, (int) ((size.height - buttonSize.height) - textArea.getSize().height) / 2);

        textArea.setForeground(Color.WHITE);
        textArea.setBackground(backgroundColor);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        try {

            String yesLabel = "YES", noLabel = "   NO";

            int fontSize = 1;
            Dimension fontMAxDimension = new Dimension(buttonSize.width / 2, buttonSize.height / 2);

            Font f = view.components.fonts.PokemonFont.getFont(fontSize);

            fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, yesLabel, fontMAxDimension);

            yes = new CustomButton(yesLabel, fontSize, Color.WHITE, utilities.image.ImageUtil.changeEveryColor(background, new RGB(109, 143, 20)), buttonSize);

            fontSize = 1;

            fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, noLabel, fontMAxDimension);

            no = new CustomButton(noLabel, fontSize, Color.WHITE, utilities.image.ImageUtil.changeEveryColor(background, new RGB(158, 24, 35)), buttonSize);

            fontMAxDimension = new Dimension(buttonSize.width / 2, buttonSize.height / 4);
            fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, noLabel, fontMAxDimension);
            f = f.deriveFont(((float) fontSize));
            textArea.setFont(f);

        } catch (Exception e) {
            yes = new JButton("YES");
            no = new JButton("NO");
        }

        yes.setLocation(0, (size.height - (yes.getSize().height)));
        no.setLocation((size.width - no.getSize().width), (size.height - (yes.getSize().height)));

        yes.addActionListener((ActionEvent e) -> {

            option = true;
            mutex.release();

        });

        no.addActionListener((ActionEvent e) -> {

            option = false;
            mutex.release();

        });

        panel.addAudibleDescription(message);

        panel.add(yes);
        panel.add(no);

        panel.add(textArea);

        frame.add(panel);
        frame.setUndecorated(true);
        frame.setSize(size);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

        Thread requestFocusWindow = new Thread(() -> {

            while (!Thread.interrupted()) {

                if (!frame.isFocused()) {
                    frame.requestFocus();
                    panel.getInputDetector().requestFocus();
                }
            }

        });

        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
        }

        requestFocusWindow.start();
        panel.getAudibleDescription();

        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
        }

        requestFocusWindow.interrupt();

        frame.dispose();
        return option;
    }

}
