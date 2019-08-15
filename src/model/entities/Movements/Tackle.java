/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities.Movements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.BattleModel;
import model.MoveModel;
import model.TypeModel;
import model.entities.Pokemon;
import model.entities.Sprite;
import utilities.image.Image;

/**
 *
 * @author Adrian Vazquez
 */
public class Tackle extends Move {

    public Tackle() throws Exception {

        super("Tackle", 100, 40, 35, null, new TypeModel().getEntity("Normal"), null, null);

        setFrontAnimation(getFrontAnimation());
        setBackAnimation(getBackAnimation());

    }

    @Override
    public Sprite getFrontAnimation() {

        if (super.getFrontAnimation() == null) {

            Sprite frontAnimation;

            String URI_DEFAULT_ATTACK_FRONT = "Resources/BattleHUD/Attack/Default/FRONT";

            List<File> files = Arrays.asList(new File(URI_DEFAULT_ATTACK_FRONT).listFiles());

            List<Image> imgsFront = new ArrayList<>();

            files.forEach((f) -> {
                if (f.getName().toLowerCase().contains(".png")) {
                    try {
                        imgsFront.add(new Image(ImageIO.read(f)));
                    } catch (IOException ex) {
                        Logger.getLogger(MoveModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            frontAnimation = null;
            try {
                frontAnimation = new Sprite(imgsFront, 100);
                setFrontAnimation(frontAnimation);
            } catch (Exception ex) {
                Logger.getLogger(Tackle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return super.getFrontAnimation();
    }

    @Override
    public Sprite getBackAnimation() {

        if (super.getBackAnimation() == null) {

            Sprite backAnimation;

            String URI_DEFAULT_ATTACK_BACK = "Resources/BattleHUD/Attack/Default/BACK";
            List<File> files;

            files = Arrays.asList(new File(URI_DEFAULT_ATTACK_BACK).listFiles());

            List<Image> imgsBack = new ArrayList<>();

            files.forEach((f) -> {
                if (f.getName().toLowerCase().contains(".png")) {
                    try {
                        imgsBack.add(new Image(ImageIO.read(f)));
                    } catch (IOException ex) {
                        Logger.getLogger(MoveModel.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            });

            backAnimation = null;

            try {
                backAnimation = new Sprite(imgsBack, 100);

                super.setBackAnimation(backAnimation);

            } catch (Exception ex) {
                Logger.getLogger(Tackle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return super.getBackAnimation();

    }

    @Override
    public boolean makeEffect(BattleModel battleModel, Pokemon from, Pokemon to) {

        boolean succeed = true;

        if (fails()) {
            succeed = false;
        } else {
            Pokemon p1, p2;

            p1 = from;
            p2 = to;

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

}
