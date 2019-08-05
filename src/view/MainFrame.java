package view;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import javax.swing.JFrame;
import utilities.image.Dimensions;

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
        update();
    }

    private void update() {

        this.setResizable(false);

        if (lastComponents.isEmpty()) {
            this.setSize(Dimensions.getSelectedResolution());
        } else {
            this.setSize(lastComponents.peek().getPreferredSize());
            add(lastComponents.peek());
        }

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        revalidate();
        repaint();
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
    
    public boolean hasPrevious(){
        return lastComponents.size() > 1;
    }

}
