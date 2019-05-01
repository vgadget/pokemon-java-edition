/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Controller;
import controller.ControllerImpl;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.entities.Move;
import model.entities.Sprite;
import utilities.DisplayMessage;

/**
 *
 * @author Adrian Vazquez
 */
public class MoveModel extends EntityModelImpl<Controller, Move, Comparable> {

    public MoveModel(Controller controller) {
        super(controller, Move.class);
    }

    public static Move getDefaultMove() {

        Move defaultMove = null;

        try {
            BufferedImage frontAnimation[] = null;
            BufferedImage backAnimation[] = null;

            for (int i = 0; i < 8; i++) {
                frontAnimation[i] = ImageIO.read(new File("Resources\\BattleHUD\\Attack\\Default\\FRONT\\tile(" + i + ").png"));
                backAnimation[i] = ImageIO.read(new File("Resources\\BattleHUD\\Attack\\Default\\BACK\\tile(" + i + ").png"));
            }

            Sprite frontMoveSprite = new Sprite(frontAnimation, 10);
            Sprite backMoveSprite = new Sprite(backAnimation, 10);

            defaultMove = new Move("Tackle", 100, 40, 20, null, new TypeModel(new ControllerImpl()).getEntity("Normal"), frontMoveSprite, backMoveSprite);

        } catch (Exception ex) {
            DisplayMessage.showErrorDialog("MOVE MODEL ERROR");
        }

        return defaultMove;
    }

}
