package debug;

import controller.ControllerImpl;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.SpecieModel;
import model.TypeModel;
import model.entities.Effectiveness;
import model.entities.Specie;
import model.entities.Type;

/**
 *
 * @author Adrian Vazquez
 */
public class ModelDebug {

    private static void loadLegacyTypes() throws Exception {
        Type NORMAL = new Type("Normal", Color.GRAY);
        Type FIRE = new Type("Fire", Color.ORANGE);
        Type WATER = new Type("Water", Color.CYAN);
        Type ELECTRIC = new Type("Electric", Color.YELLOW);
        Type GRASS = new Type("Grass", Color.GREEN);
        Type ICE = new Type("Ice", Color.CYAN.brighter().brighter());
        Type FIGHTING = new Type("Fighting", Color.ORANGE.darker().darker());
        Type POISON = new Type("Poison", Color.MAGENTA.darker());
        Type GROUND = new Type("Ground", Color.YELLOW.darker().darker());
        Type FLYING = new Type("Flying", Color.CYAN.darker());
        Type PSYCHIC = new Type("Psychic", Color.PINK);
        Type BUG = new Type("Bug", Color.GREEN.darker().darker());
        Type ROCK = new Type("Rock", Color.YELLOW.darker().darker().darker());
        Type GHOST = new Type("Ghost", Color.MAGENTA.darker().darker().darker());
        Type DRAGON = new Type("Dragon", Color.MAGENTA.darker().darker());
        Type DARK = new Type("Dark", Color.GRAY);
        Type STEEL = new Type("Steel", Color.LIGHT_GRAY);;
        Type FAIRY = new Type("Fairy", Color.PINK.brighter().brighter());

        TypeModel typeModelWritter = new TypeModel(new ControllerImpl());

        typeModelWritter.newEntity(NORMAL);
        typeModelWritter.newEntity(FIRE);
        typeModelWritter.newEntity(POISON);
        typeModelWritter.newEntity(WATER);
        typeModelWritter.newEntity(ELECTRIC);
        typeModelWritter.newEntity(GRASS);
        typeModelWritter.newEntity(ICE);
        typeModelWritter.newEntity(FIGHTING);
        typeModelWritter.newEntity(POISON);
        typeModelWritter.newEntity(GROUND);
        typeModelWritter.newEntity(FLYING);
        typeModelWritter.newEntity(PSYCHIC);
        typeModelWritter.newEntity(BUG);
        typeModelWritter.newEntity(ROCK);
        typeModelWritter.newEntity(GHOST);
        typeModelWritter.newEntity(DRAGON);
        typeModelWritter.newEntity(DARK);
        typeModelWritter.newEntity(STEEL);
        typeModelWritter.newEntity(FAIRY);

        // NORMAL TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, NORMAL, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, NORMAL, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, NORMAL, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, NORMAL, FAIRY));

        // FIRE TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIRE, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIRE, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIRE, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIRE, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIRE, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIRE, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIRE, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIRE, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIRE, FAIRY));

        // WATER TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, WATER, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, WATER, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, WATER, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, WATER, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, WATER, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, WATER, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, WATER, FAIRY));

        // ELECTRIC TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ELECTRIC, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ELECTRIC, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ELECTRIC, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, ELECTRIC, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ELECTRIC, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ELECTRIC, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ELECTRIC, FAIRY));

        // GRASS TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GRASS, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GRASS, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GRASS, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GRASS, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GRASS, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GRASS, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GRASS, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GRASS, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GRASS, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GRASS, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GRASS, FAIRY));

        // ICE TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ICE, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ICE, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ICE, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ICE, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ICE, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ICE, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ICE, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ICE, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ICE, FAIRY));

        // FIGHTING TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIGHTING, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIGHTING, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIGHTING, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIGHTING, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIGHTING, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIGHTING, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIGHTING, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIGHTING, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIGHTING, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIGHTING, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIGHTING, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIGHTING, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIGHTING, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, FIGHTING, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FIGHTING, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIGHTING, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FIGHTING, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FIGHTING, FAIRY));

        // POISON TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, POISON, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, POISON, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, POISON, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, POISON, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, POISON, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, POISON, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, POISON, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, POISON, FAIRY));

        // GROUND TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GROUND, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GROUND, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GROUND, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GROUND, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, GROUND, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GROUND, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GROUND, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GROUND, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GROUND, FAIRY));

        // FLYING TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FLYING, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FLYING, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FLYING, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FLYING, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FLYING, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FLYING, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FLYING, FAIRY));

        // PSYCHIC TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, PSYCHIC, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, PSYCHIC, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, PSYCHIC, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, PSYCHIC, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, PSYCHIC, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, PSYCHIC, FAIRY));

        // BUG TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, BUG, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, BUG, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, BUG, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, BUG, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, BUG, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, BUG, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, BUG, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, BUG, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, BUG, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, BUG, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, BUG, FAIRY));

        // ROCK TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ROCK, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ROCK, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ROCK, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ROCK, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ROCK, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, ROCK, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, ROCK, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, ROCK, FAIRY));

        // GHOST TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GHOST, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, GHOST, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, GHOST, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, GHOST, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, GHOST, FAIRY));

        // DRAGON TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, DRAGON, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DRAGON, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, DRAGON, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0, DRAGON, FAIRY));

        // DARK TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, DARK, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, DARK, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, DARK, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, DARK, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, DARK, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, DARK, FAIRY));

        // STEEL TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, STEEL, STEEL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, STEEL, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, STEEL, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, STEEL, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, STEEL, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, STEEL, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, STEEL, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, STEEL, FAIRY));

        // FAIRY TYPE GRAPH
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, FAIRY));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FAIRY, FIRE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, WATER));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, ELECTRIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, NORMAL));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, ICE));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FAIRY, FIGHTING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FAIRY, POISON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, GROUND));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, FLYING));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, PSYCHIC));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, GRASS));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, BUG));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, ROCK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(1, FAIRY, GHOST));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FAIRY, DRAGON));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(2, FAIRY, DARK));
        typeModelWritter.getEffectivenessGraph().addEffectiveness(new Effectiveness(0.5f, FAIRY, STEEL));

    }

    public static void testTypeModel() throws Exception {

        TypeModel typeModelReader = new TypeModel(new ControllerImpl());

        System.out.println("Debug available types: " + typeModelReader.getAll());

        System.out.println(typeModelReader.getEffectivenessGraph().toString());
    }

    public static void testSpecie() throws Exception {

        SpecieModel sm = new SpecieModel(new ControllerImpl());

//        TypeModel tm = new TypeModel(new ControllerImpl());
//
//        BufferedImage frontSprite[] = new BufferedImage[20];
//        BufferedImage backSprite[] = new BufferedImage[20];
//
//        for (int i = 0; i < 20; i++) {
////            try {
////                frontSprite[i] = ImageIO.read(new File("Resources/BattleHUD/Pokedex/MISSINGNO/Sprite/FRONT/tile(" + i + ").png"));
////                backSprite[i] = ImageIO.read(new File("Resources/BattleHUD/Pokedex/MISSINGNO/Sprite/BACK/tile(" + i + ").png"));
////                
////            } catch (Exception ex) {
////                System.err.println(ex);
////            }
////
////        }
//
//        sm.newEntity(new Specie("Mew", tm.getEntity("Psychic"), null, new Sprite(frontSprite, 80), new Sprite(backSprite, 80), 10, 10, 151, 20, 10, 20, 10, 20, 10, 20, 10, 20, 10, 20, 10, 1, 1));

        System.out.println(sm.getAll().get(0).getBackSprite().getRefreshRate());
        
        showSpecieBack(sm.getAll().get(0));

    }

    public static void showImage(BufferedImage img) {

        JPanel panel = new ShowImage(img);
        JFrame frame = new JFrame("VENTANA");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static void showSpecieBack(Specie s) {

        BufferedImage imgs[] = s.getBackSprite().getAnimation();

        JPanel panel = new ShowGraphics(imgs, s.getBackSprite().getRefreshRate());
        JFrame frame = new JFrame("VENTANA");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
