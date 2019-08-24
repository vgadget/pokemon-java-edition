package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Stack;
import javax.swing.JFrame;
import utilities.image.Dimensions;
import view.components.AidPanelImpl;
import view.components.AidView;
import view.components.Musical;

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

        Runtime.getRuntime().gc();

        if (!lastComponents.isEmpty()) {

            add(lastComponents.peek());

            utilities.sound.SoundPlayer.getInstance().stopMusic();

            if (lastComponents.peek() instanceof Musical) {

                ((Musical) lastComponents.peek()).playBackgroundMusic();
            }

            if (lastComponents.peek() instanceof AidView) {
                ((AidView) lastComponents.peek()).getAudibleDescription();
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
        }
        update();
    }

    public boolean hasPrevious() {
        return lastComponents.size() > 1;
    }

}
