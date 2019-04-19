package debug;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import model.pokemon.Move;
import model.pokemon.MoveSet;
import model.pokemon.Type;
import view.components.AidPanel;
import view.components.ButtonFactory;
import view.components.CustomButton;

/**
 *
 * @author Adrian Vazquez
 */
public class ViewDebug {

    public static void test() throws Exception {
        AidPanel panel = new AidPanel();

        Type fire = new Type("FIRE", Color.RED.brighter());
        Type water = new Type("WATER", Color.CYAN);

        BufferedImage[] animaton = new BufferedImage[1];
        animaton[0] = ImageIO.read(new File("example.png"));

        Move one = new Move("firstMove", 10, 10, 10, null, fire, animaton);
        Move two = new Move("secondMove", 20, 20, 20, null, water, animaton);

        MoveSet moveSet = new MoveSet();
        moveSet.add(one);
        moveSet.add(two);

        List<CustomButton> buttons = ButtonFactory.moveSetButtons(moveSet);

        buttons.forEach((cb) -> {
            panel.add(cb);
        });

        JFrame frame = new JFrame("VENTANA");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
