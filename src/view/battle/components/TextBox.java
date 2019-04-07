package view.battle.components;

import utilities.Dimensions;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import view.components.fonts.PokemonFont;

/**
 *
 * @author Adrian Vazquez
 */
public class TextBox extends JPanel {

    private static final String URI_TEXTBOX_BACKGROUND = "Resources/BattleHUD/TextBox/textBox.png";
    private static final String URI_TEXTBOX_INTRO_KEY = "Resources/BattleHUD/TextBox";

    private static final int MAX_CAR = 25; //The maximum characters that can be displayed at a time.

    
    private BufferedImage textbackground; //The image that surrounds the text.  
    private int textbackgroundLocationX, textbackgroundLocationY;

    private BufferedImage[] introKey; // Images who give the intro key animation.
    private int currentSprite, introKeyLocationX, introKeyLocationY;

    private Queue<String> textQueue; // If the text is longer that MAX_CAR it cannot be displayed. We use textQueue to store and show the text in slices of MAX_CAR size.

    protected JTextArea text; //Text displayed on screen

    protected JLabel inputDetector; // Hidden object, it detects any input from de keyboard or mouse (click only on the image) to show the following text in the queue.

    public TextBox(Dimension frameDimension) {

        setLayout(null); //Allows free location of Java swing objects. 

        //Initialize
        textQueue = new LinkedBlockingQueue<>();
        text = new JTextArea("");
        text.setEditable(false);
        text.setVisible(false);

        try {

            introKey = new BufferedImage[2];
            for (int i = 0; i < introKey.length; i++) {
                introKey[i] = ImageIO.read(new File(URI_TEXTBOX_INTRO_KEY + "/tile(" + i + ").png"));
                introKey[i] = utilities.ImageUtil.resizeProportional(introKey[i], 0.1);
            }

            
            //Load text background
            textbackground = ImageIO.read(new File(URI_TEXTBOX_BACKGROUND));

            //Load font
            text.setFont(PokemonFont.getFont(16));

        } catch (Exception e) {

            new Thread(() -> {
                JOptionPane.showMessageDialog(null, "ERROR 001: MISSING FONT", "ERROR", 0);
            }).start();

        }

        
        //Set location and size depending of choosen resolution.
        if (frameDimension.equals(Dimensions.frameDimension720p)) {

            this.textbackground = utilities.ImageUtil.resizeProportional(textbackground, 2);
            this.textbackgroundLocationX = 0;
            this.textbackgroundLocationY = (int) (frameDimension.getHeight() - 135);

            this.introKeyLocationX = textbackgroundLocationX + 475;
            this.introKeyLocationY = textbackgroundLocationY + 50;

            text.setSize(this.textbackground.getWidth() - 70, 50);
            text.setLocation(25, (int) (frameDimension.getHeight() - 115));

        } else if (frameDimension.equals(Dimensions.frameDimension1080p)) {
            this.textbackground = utilities.ImageUtil.resizeProportional(textbackground, 2);
            this.textbackgroundLocationX = 0;
            this.textbackgroundLocationY = (int) (frameDimension.getHeight() - 135);

            this.introKeyLocationX = textbackgroundLocationX + 475;
            this.introKeyLocationY = textbackgroundLocationY + 50;

            text.setSize(this.textbackground.getWidth() - 70, 50);
            text.setLocation(25, (int) (frameDimension.getHeight() - 115));
        }

        inputDetector = new JLabel();

        inputDetector.setBounds(this.textbackgroundLocationX, textbackgroundLocationY, this.textbackground.getWidth(), this.textbackground.getHeight());

        inputDetector.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dequeueText();
            }
        });

        inputDetector.setFocusable(true);

        inputDetector.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                dequeueText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        new Thread(() -> {
            while (true) {
                try {
                    
                    if (currentSprite >= introKey.length) {
                        currentSprite = 0;
                    }
                    
                    Thread.sleep(500);
                    
                    currentSprite++;
                    
                } catch (InterruptedException ex) {
                    
                }
            }
        }).start();

    }

    public void enqueueText(String text) {

        if (!text.equals("")) {

            if (text.length() > 2 * MAX_CAR) {

                enqueueText(text.substring(0, MAX_CAR));
                enqueueText(text.substring(MAX_CAR, text.length()));

            } else {

                if (text.length() > MAX_CAR) {

                    textQueue.add(text.substring(0, MAX_CAR));
                    textQueue.add(text.substring(MAX_CAR, text.length()));

                } else {
                    textQueue.add(text);
                }
            }
        }
    }

    public void dequeueText() {

        if (hasText()) {
            String s1 = "", s2 = "";

            if (hasText()) {
                s1 = this.textQueue.remove();
            }

            if (hasText()) {
                s2 = this.textQueue.remove();
            }

            this.text.setText(s1 + "\n" + s2);
            this.text.setVisible(true);

        } else {

            this.text.setVisible(false);
            this.text.setText("");
        }

    }

    public boolean hasText() {
        return this.textQueue.size() > 0;
    }

    @Override
    public void paintComponent(Graphics g) {

        if (hasText() || !text.getText().equals("")) {
           
            if (text.getText().equals("")) { //If there are text on the queue, load next text on the queue. 
                dequeueText();
            }

            g.drawImage(textbackground, textbackgroundLocationX, textbackgroundLocationY, this);
            g.drawImage(this.introKey[currentSprite], this.introKeyLocationX, this.introKeyLocationY, this);

            inputDetector.requestFocusInWindow(); // Each time some text pop-up, it request keyBoard attention over the other elements. If receive some imputs from the keyboard or mouse, load next text on queuse 

        }
    }
    
    public JLabel getInputDetector(){
        return inputDetector;
    }

    public JTextArea getTextComponent() {
        return text;
    }
    
    

}
