package model;

import controller.Controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import model.entities.Move;
import model.entities.Pokemon;
import model.entities.Sprite;
import model.entities.StatusCondition;
import model.entities.Type;
import utilities.image.Image;

/**
 *
 * @author Adrian Vazquez
 */
public class MoveModel extends AbstractModel<Controller, Move, Comparable> {

    public MoveModel() {
        super(Move.class);
    }

    public static Move getDefaultMove() {

        String name = "Tackle";
        Integer precision = 100;
        Integer power = 40;
        Integer pp = 35;
        StatusCondition secondaryEffect = null;
        Type type = new TypeModel().getEntity("Normal");
        Sprite frontAnimation;
        Sprite backAnimation;

        String URI_DEFAULT_ATTACK_FRONT = "Resources/BattleHUD/Attack/Default/FRONT";
        String URI_DEFAULT_ATTACK_BACK = "Resources/BattleHUD/Attack/Default/BACK";

        Move defaultMove = null;

        try {

            List<File> files;

            files = Arrays.asList(new File(URI_DEFAULT_ATTACK_FRONT).listFiles());
 
            List<Image> imgsFront = new ArrayList<>();

            files.forEach((f) -> {
                if (f.getName().toLowerCase().contains(".png")) {
                    try {
                        imgsFront.add(new Image(ImageIO.read(f)));
                    } catch (IOException ex) {
                    }
                }
            });

            files = Arrays.asList(new File(URI_DEFAULT_ATTACK_BACK).listFiles());
            List<Image> imgsBack = new ArrayList<>();

            files.forEach((f) -> {
                if (f.getName().toLowerCase().contains(".png")) {
                    try {
                        imgsBack.add(new Image(ImageIO.read(f)));
                    } catch (IOException ex) {
                    }
                }
            });

            frontAnimation = new Sprite(imgsFront, 100);
            backAnimation = new Sprite(imgsBack, 100);

            defaultMove = new Move(name, precision, power, pp, secondaryEffect, type, frontAnimation, backAnimation) {

                @Override
                public boolean makeEffect(BattleModel battleModel) {

                    boolean succeed = true;

                    if (fails()) {
                        succeed = false;
                    } else {
                        Pokemon p1, p2;

                        p1 = battleModel.getTrainerP1().getSelectedPokemon();

                        if (p1.getMoveSet().getSelectedMove() == this) {

                            p2 = battleModel.getTrainerP2().getSelectedPokemon();

                        } else {
                            p2 = battleModel.getTrainerP1().getSelectedPokemon();
                            p1 = battleModel.getTrainerP2().getSelectedPokemon();
                        }

                        Double hp = p2.getCurrentHp() - battleModel.calculateDamage(p1, p2);

                        hp = Math.round(hp) + 0d;

                        if (hp < 0) {
                            hp = 0d;
                        } else if (hp > p2.getMaxHp()) {
                            hp = p2.getMaxHp() + 0d;
                        }

                        p2.setCurrentHp(hp.intValue());
                    }

                    return succeed;
                }

            };

        } catch (Exception ex) {
            utilities.DisplayMessage.showErrorDialog(ex.toString());
        }
        return defaultMove;
    }
}
