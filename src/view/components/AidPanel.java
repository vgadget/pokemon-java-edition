package view.components;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import languajes.Text;
import texttospeech.Narrator;

public class AidPanel extends JPanel {

    private final List<AidComponent> aidComponents = new ArrayList<>();
    private JLabel inputDetector = new JLabel();

    public AidPanel() {

        initComponents();
    }

    private void initComponents() {
        new Thread(() -> {
            inputDetector.setFocusable(true);
            inputDetector.addKeyListener(new KeyAdapter() {
                
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyChar() >= '1' && e.getKeyChar() <= '9') {
                        int opc = Integer.parseInt(e.getKeyChar() + "") - 1;
                        if (opc < aidComponents.size()) {
                            aidComponents.get(opc).press();
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        getAudibleDescription();
                    }
                }
                
            });
            
            inputDetector.requestFocus();
            add(inputDetector);
            
            getAudibleDescription();
        }).start();
    }

    @Override
    public Component add(Component c) {

        addAidComponent(c);
        return super.add(c);
    }

    @Override
    public Component add(Component c, int index) {
        addAidComponent(c);
        return super.add(c, index);
    }

    @Override
    public void add(Component c, Object constraint) {
        addAidComponent(c);
        super.add(c, constraint);
    }

    @Override
    public void add(Component c, Object constraint, int index) {
        addAidComponent(c);
        super.add(c, constraint, index);
    }

    @Override
    public void remove(Component c) {
        if (c instanceof AidComponent) {
            aidComponents.remove((AidComponent) c);
        }

        super.remove(c);
    }

    private void addAidComponent(Component component) {
        if (component instanceof AidComponent) {
            if (!aidComponents.contains((AidComponent) component) && aidComponents.size() < 9) {
                this.aidComponents.add((AidComponent) component);
            }
        }
    }

    private void getAudibleDescription() {

        new Thread(() -> {

            String r = "";

            for (int i = 0; i < aidComponents.size(); i++) {

                int n = i + 1;
                r += Text.pressKeyTo(n + "", aidComponents.get(i).getDescription());
            }

            if (!r.equalsIgnoreCase("")) {
                r += "\n" + Text.pressSpaceToRepeat();
                Narrator.getInstance().speak(r);
            } else {
                Narrator.getInstance().speak(Text.pressSpaceToGetAudibleDescriptions());
            }

        }).start();
    }

}
