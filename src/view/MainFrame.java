package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Stack;
import javax.swing.JFrame;
import utilities.image.Dimensions;
import view.components.AidPanel;
import view.menu.components.AnimatedBackgroundPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class MainFrame extends JFrame {

    private static MainFrame frame;

    public static MainFrame getInstance() {

        if (frame == null) {
            frame = new MainFrame();
        }

        return frame;
    }

    private Stack<Component> lastComponents;

    private MainFrame() {
        super();
        lastComponents = new Stack<>();
        setBackground(Color.BLACK);
        setUndecorated(true);
        setVisible(true);

        update();
    }

    private void update() {

        this.setSize(Dimensions.getSelectedResolution());
        this.setResizable(false);

        if (!lastComponents.isEmpty()) {

            add(lastComponents.peek());

            utilities.sound.SoundPlayer.getInstance().stopMusic();

            if (lastComponents.peek() instanceof AnimatedBackgroundPanel) {

                ((AnimatedBackgroundPanel) lastComponents.peek()).playBackgroundMusic();
            }
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);

        revalidate();
        repaint();
    }

    public void hideMainFrame() {
        setVisible(false);
    }

    public void showMainFrame() {
        setVisible(true);
    }

    public void showView(Container c) {

        if (!lastComponents.isEmpty()) {
            remove(lastComponents.peek());
        }
        lastComponents.push(c);
        update();

    }

    public void previousView() {

        if (hasPrevious()) {
            remove(lastComponents.pop());

            if (lastComponents.peek() instanceof AidPanel) {
                ((AidPanel) lastComponents.peek()).getAudibleDescription();
            }
        }
        update();
    }

    public boolean hasPrevious() {
        return lastComponents.size() > 1;
    }

}
