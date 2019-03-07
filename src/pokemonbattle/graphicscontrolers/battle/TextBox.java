package pokemonbattle.graphicscontrolers.battle;

import Util.Dimensions;
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

/**
 *
 * @author Adrian Vazquez
 */
public class TextBox extends JPanel {

    private static final String URI_TEXTBOX_BACKGROUND = "Resources/BattleHUD/TextBox/textBox.png";
    private static final String URI_TEXTBOX_INTRO_KEY = "Resources/BattleHUD/TextBox";

    private static final int MAX_CAR = 25;

    private BufferedImage textbackground;
    private int textbackgroundLocationX, textbackgroundLocationY;

    private BufferedImage[] introKey;
    private int currentSprite, introKeyLocationX, introKeyLocationY;

    private Queue<String> textQueue;

    protected JTextArea text;

    protected JLabel inputDetector;

    public TextBox(Dimension frameDimension) {

        setLayout(null);

        textQueue = new LinkedBlockingQueue<>();

        text = new JTextArea("");
        text.setEditable(false);
        text.setVisible(false);

        try {

            introKey = new BufferedImage[2];
            for (int i = 0; i < introKey.length; i++) {
                introKey[i] = ImageIO.read(new File(URI_TEXTBOX_INTRO_KEY + "/tile(" + i + ").png"));
                introKey[i] = Util.ImageUtil.resizeProportional(introKey[i], 0.1);
            }

            //Cargar el fondo
            textbackground = ImageIO.read(new File(URI_TEXTBOX_BACKGROUND));

            //Cargar la fuente
            InputStream is = getClass().getResourceAsStream("HUD_NAME_FONT.ttf");
            Font f = Font.createFont(Font.TRUETYPE_FONT, is);
            f = f.deriveFont(0, 16);
            text.setFont(f);

        } catch (Exception e) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "ERROR 001: MISSING FONT", "ERROR", 0);
                }
            }).start();

        }

        if (frameDimension.equals(Dimensions.frameDimension720p)) {

            this.textbackground = Util.ImageUtil.resizeProportional(textbackground, 2);
            this.textbackgroundLocationX = 0;
            this.textbackgroundLocationY = (int) (frameDimension.getHeight() - 135);

            this.introKeyLocationX = textbackgroundLocationX + 475;
            this.introKeyLocationY = textbackgroundLocationY + 50;

            text.setSize(this.textbackground.getWidth() - 70, 50);
            text.setLocation(25, (int) (frameDimension.getHeight() - 115));

        } else if (frameDimension.equals(Dimensions.frameDimension1080p)) {
            this.textbackground = Util.ImageUtil.resizeProportional(textbackground, 2);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
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

        if (haveText()) {
            String s1 = "", s2 = "";

            if (haveText()) {
                s1 = this.textQueue.remove();
            }

            if (haveText()) {
                s2 = this.textQueue.remove();
            }

            this.text.setText(s1 + "\n" + s2);
            this.text.setVisible(true);

        } else {

            this.text.setVisible(false);
            this.text.setText("");
        }

    }

    public boolean haveText() {
        return this.textQueue.size() > 0;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (haveText() || !text.getText().equals("")) {
            if (text.getText().equals("")) {
                dequeueText();
            }

            g.drawImage(textbackground, textbackgroundLocationX, textbackgroundLocationY, this);
            g.drawImage(this.introKey[currentSprite], this.introKeyLocationX, this.introKeyLocationY, this);

            inputDetector.requestFocusInWindow();

        }
    }

}
