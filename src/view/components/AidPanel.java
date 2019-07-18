package view.components;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import languajes.NarratorTexts;
import texttospeech.Narrator;

public class AidPanel extends JPanel {

    private List<AidComponent> currentAidComponents = new ArrayList<>();
    private List<AidComponent> allAidComponents = new ArrayList<>();
    private List<String> additionalDescriptions = new ArrayList<>();

    private int componentBlockFrom = 0;
    private int componentBlockTo = 9;

    private JLabel inputDetector = new JLabel();

    private boolean enabledAudioDescription;

    public AidPanel() {

        
        enabledAudioDescription = false;
        initComponents();

    }

    public boolean isEnabledAudioDescription() {
        return enabledAudioDescription;
    }

    public void setEnabledAudioDescription(boolean enabled) {
        this.enabledAudioDescription = enabled;
    }

    private void initComponents() {

        new Thread(() -> {
            inputDetector.setFocusable(true);
            inputDetector.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    inputDetector.requestFocus();

                    if (e.getKeyChar() >= '1' && e.getKeyChar() <= '9') {

                        int opc = Integer.parseInt(e.getKeyChar() + "") - 1;

                        if (opc < currentAidComponents.size()) {
                            currentAidComponents.get(opc).press();
                        }

                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                        getAudibleDescription();

                    } else if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) && allAidComponents.size() > 9) {

                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                            if (componentBlockTo + 9 < allAidComponents.size()) {

                                componentBlockFrom = componentBlockTo;
                                componentBlockTo = componentBlockTo + 9;

                            } else if (componentBlockTo < allAidComponents.size()) {
                                componentBlockFrom = componentBlockTo;
                                componentBlockTo = allAidComponents.size();
                            }

                        } else {

                            if (componentBlockFrom - 9 >= 0) {

                                componentBlockTo = componentBlockFrom;
                                componentBlockFrom = componentBlockFrom - 9;

                            } else {
                                componentBlockFrom = 0;
                                componentBlockTo = componentBlockFrom + 9;
                            }
                        }

                        currentAidComponents = allAidComponents.subList(componentBlockFrom, componentBlockTo);
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
        inputDetector.requestFocus();

        return super.add(c);
    }

    @Override
    public Component add(Component c, int index) {
        addAidComponent(c);
        inputDetector.requestFocus();
        return super.add(c, index);
    }

    @Override
    public void add(Component c, Object constraint) {
        addAidComponent(c);
        super.add(c, constraint);
        inputDetector.requestFocus();

    }

    @Override
    public void add(Component c, Object constraint, int index) {
        addAidComponent(c);
        super.add(c, constraint, index);
        inputDetector.requestFocus();
    }

    @Override
    public void remove(Component c) {
        if (c instanceof AidComponent) {
            currentAidComponents.remove((AidComponent) c);
            allAidComponents.remove((AidComponent) c);
        }
        super.remove(c);
        inputDetector.requestFocus();

    }

    private void addAidComponent(Component component) {
        if (component instanceof AidComponent) {

            if (!allAidComponents.contains((AidComponent) component)) {
                if (!currentAidComponents.contains((AidComponent) component) && currentAidComponents.size() < 9) {
                    this.currentAidComponents.add((AidComponent) component);
                    inputDetector.requestFocus();
                }

                this.allAidComponents.add((AidComponent) component);
            }
        }
    }

    public void addAudibleDescription(String s) {

        this.additionalDescriptions.add(s);

    }

    private void getAudibleDescription() {

        if (isEnabledAudioDescription()) {
            new Thread(() -> {

                String r = "";

                r = this.additionalDescriptions
                        .stream()
                        .map((s) -> s + "..\n\n")
                        .reduce(r, String::concat);

                for (int i = 0; i < currentAidComponents.size(); i++) {

                    int n = i + 1;
                    r += NarratorTexts.pressKeyTo(n + "", currentAidComponents.get(i).getDescription()) + "\n";
                }

                if (!r.equalsIgnoreCase("")) {

                    if (this.allAidComponents.size() > 9) {
                        r += "\n" + NarratorTexts.pressUpOrDownArrowToNavigateBetweenMoreOptions();
                    }

                    r += "\n" + NarratorTexts.pressSpaceToRepeat();

                    Narrator.getInstance().speak(r);

                } else {
                    Narrator.getInstance().speak(NarratorTexts.pressSpaceToGetAudibleDescriptions());
                }

                inputDetector.requestFocus();

            }).start();
        }
    }

}
