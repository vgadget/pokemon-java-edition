package debug;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import model.pokemon.Move;
import model.pokemon.MoveSet;
import model.pokemon.Sprite;
import model.pokemon.Type;
import model.persistence.Dao;
import model.persistence.FileDao;
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

        Sprite s = new Sprite(animaton, 1);

        model.persistence.Persistence.getInstance().getDao().save(water);
        Move one = (Move) model.persistence.Persistence.getInstance().getDao().getAll(Move.class).get(0);
        Move two = (Move) model.persistence.Persistence.getInstance().getDao().getAll(Move.class).get(1);

        MoveSet moveSet = new MoveSet();
        moveSet.add(one);
        moveSet.add(two);

        Dao<Move, String> dao = new FileDao<>();
        dao.save(one);
        dao.save(two);

        Move m = moveSet.getMoves().next();

        moveSet.setRemainingPP(m, 5);

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
