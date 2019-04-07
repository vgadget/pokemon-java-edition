package debug;

import view.battle.BattleView;
import utilities.Dimensions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import view.battle.BattleViewImpl;
import view.battle.components.PokemonMainCharacterHealthHud;
import view.battle.components.PokemonOpponentHealthHud;

/**
 *
 * @author Adrian Vazquez
 */
public class PokemonBattleViewDebug {

    public static void test() {
        JFrame testOutputFrame = new JFrame("Debug: Outputs");
        JFrame testInputFrame = new JFrame("Debug: Inputs");

        Dimension screenSize = Dimensions.frameDimension720p;
        Dimension testOutputframeSize = new Dimension(screenSize);

        Dimension testInputputframeSize = new Dimension((int) (500), (int) (600));

        //testOuputElements
        BattleView HUD = new BattleViewImpl(screenSize);
        HUD.setBackground(Color.DARK_GRAY);

        JPanel debug = new JPanel();

        JToggleButton playPauseAnimations = new JToggleButton("Play/Pause animations");

        playPauseAnimations.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.pausePokemonSprites();
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.playPokemonSprites();
                }
            }
        });

        //Player 1
        debug.add(new JLabel("Player1 - Main"));

        JTextField nameP1, levelP1, currentPsP1, MaxPsP1;
        JRadioButton sexP1Male, sexP1Female, sexP1None;
        JToggleButton withdrawP1 = new JToggleButton("Withdraw");
        JToggleButton blinkP1 = new JToggleButton("Blink");
        JButton deadP1 = new JButton("Dead");
        JButton appearP1 = new JButton("Appear");
        JButton launchPokeballP1 = new JButton("Pokeball");
        JButton goP1 = new JButton("Let's Go");
        JButton flyP1 = new JButton("Fly");
        JToggleButton stateP1 = new JToggleButton("Non-volatile status");
        JToggleButton state2P1 = new JToggleButton("Volatile status");

        JButton changeStatsUpP1 = new JButton("Stats UP");
        JButton changeStatsDownP1 = new JButton("Stats DOWN");
        JButton makeAttackP1 = new JButton("Attack");

        sexP1Female = new JRadioButton("Female");
        sexP1Male = new JRadioButton("Male");
        sexP1None = new JRadioButton("None");

        ButtonGroup sexSelector = new ButtonGroup();

        sexSelector.add(sexP1Female);
        sexSelector.add(sexP1Male);
        sexSelector.add(sexP1None);

        sexSelector.setSelected(sexP1None.getModel(), true);

        makeAttackP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        HUD.player1LoadDefaultAttackAnimation();
                        HUD.doAttackAnimation();

                        while (HUD.attackAnimationIsPplaying()) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PokemonBattleViewDebug.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        HUD.player2Blink();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PokemonBattleViewDebug.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        HUD.player2Blink();

                    }
                }).start();

            }
        });

        changeStatsUpP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HUD.player1StatsUP();

            }
        });

        changeStatsDownP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HUD.player1StatsDown();
            }
        });

        stateP1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.player1SetNonVolatileStatus(null);
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.player1RemoveNonVolatileStatus();
                }
            }
        });

        state2P1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.player1SetVolatileStatus(null);
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.player1RemoveVolatileStatus();
                }
            }
        });

        flyP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player1Fly();
            }
        });

        goP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player1LetsGo();
            }
        });

        launchPokeballP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player1LaunchPokeball();
            }
        });

        deadP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player1Dead();
            }
        });

        appearP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player1Appear();
            }
        });

        blinkP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player1Blink();
            }
        });

        withdrawP1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.player1Withdraw();
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.player1Appear();
                }
            }
        });

        nameP1 = new JTextField("Mew");
        levelP1 = new JTextField("50");
        currentPsP1 = new JTextField("50");
        MaxPsP1 = new JTextField("999");

        debug.add(new JLabel("Name: "));
        debug.add(nameP1);
        debug.add(new JLabel("Level: "));
        debug.add(levelP1);
        debug.add(new JLabel("Current PS: "));
        debug.add(currentPsP1);
        debug.add(new JLabel("MaxPS"));
        debug.add(MaxPsP1);
        debug.add(new JLabel("Sex: "));
        debug.add(sexP1Female);
        debug.add(sexP1Male);
        debug.add(sexP1None);
        debug.add(withdrawP1);
        debug.add(blinkP1);
        debug.add(deadP1);
        debug.add(appearP1);
        debug.add(launchPokeballP1);
        debug.add(goP1);
        debug.add(flyP1);
        debug.add(stateP1);
        debug.add(state2P1);
        debug.add(changeStatsUpP1);
        debug.add(changeStatsDownP1);
        debug.add(makeAttackP1);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(500, 1));
        debug.add(separator);

        //Player 2
        debug.add(new JLabel("Player2 - Opponent"));

        JTextField nameP2, levelP2, currentPsP2, MaxPsP2;

        JRadioButton sexP2Male, sexP2Female, sexP2None;

        JToggleButton withdrawP2 = new JToggleButton("Withdraw");

        JToggleButton blinkP2 = new JToggleButton("Blink");

        JButton deadP2 = new JButton("Dead");
        JButton appearP2 = new JButton("Appear");

        JButton launchPokeballP2 = new JButton("Pokeball");

        JButton goP2 = new JButton("Let's Go");
        JButton flyP2 = new JButton("Fly");

        JToggleButton stateP2 = new JToggleButton("Non-volatile status");
        JToggleButton state2P2 = new JToggleButton("Volatile status");

        JButton changeStatsUpP2 = new JButton("Stats UP");
        JButton changeStatsDownP2 = new JButton("Stats DOWN");
        JButton makeAttackP2 = new JButton("Attack");

        makeAttackP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        HUD.player2LoadDefaultAttackAnimation();
                        HUD.doAttackAnimation();

                        while (HUD.attackAnimationIsPplaying()) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PokemonBattleViewDebug.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        HUD.player1Blink();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PokemonBattleViewDebug.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        HUD.player1Blink();

                    }
                }).start();

            }
        });

        changeStatsUpP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HUD.player2StatsUP();

            }
        });

        changeStatsDownP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HUD.player2StatsDown();

            }
        });

        stateP2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.player2SetNonVolatileStatus(null);
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.player2RemoveNonVolatileStatus();
                }
            }
        });

        state2P2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.player2SetVolatileStatus(null);
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.player2RemoveVolatileStatus();
                }
            }
        });

        flyP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player2Fly();
            }
        });

        goP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player2LetsGo();
            }
        });

        launchPokeballP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player2LaunchPokeball();
            }
        });

        deadP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player2Dead();
            }
        });

        appearP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player2Appear();
            }
        });

        JCheckBox catched = new JCheckBox("Catched");

        sexP2Female = new JRadioButton("Female");
        sexP2Male = new JRadioButton("Male");
        sexP2None = new JRadioButton("None");

        ButtonGroup sexSelectorP2 = new ButtonGroup();

        sexSelectorP2.add(sexP2Female);
        sexSelectorP2.add(sexP2Male);
        sexSelectorP2.add(sexP2None);

        sexSelectorP2.setSelected(sexP2None.getModel(), true);

        nameP2 = new JTextField("Opponent");
        levelP2 = new JTextField("45");
        currentPsP2 = new JTextField("50");
        MaxPsP2 = new JTextField("999");

        debug.add(new JLabel("Name: "));
        debug.add(nameP2);
        debug.add(new JLabel("Level: "));
        debug.add(levelP2);
        debug.add(new JLabel("Current PS: "));
        debug.add(currentPsP2);
        debug.add(new JLabel("MaxPS: "));
        debug.add(MaxPsP2);
        debug.add(new JLabel("Sex: "));

        blinkP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.player2Blink();
            }
        });

        debug.add(sexP2Female);
        debug.add(sexP2Male);
        debug.add(sexP2None);
        debug.add(catched);
        debug.add(withdrawP2);
        debug.add(blinkP2);
        debug.add(deadP2);
        debug.add(appearP2);
        debug.add(launchPokeballP2);
        debug.add(goP2);
        debug.add(flyP2);
        debug.add(stateP2);
        debug.add(state2P2);
        debug.add(changeStatsUpP2);
        debug.add(changeStatsDownP2);
        debug.add(makeAttackP2);

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(500, 1));
        debug.add(separator2);

        withdrawP2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.player2Withdraw();
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.player2Appear();
                }
            }
        });

        JButton updateFields = new JButton("UPDATE DATA");

        updateFields.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                final int sex;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HUD.pausePokemonSprites();
                    }
                }).start();

                if (sexSelector.isSelected(sexP1Female.getModel())) {

                    sex = PokemonMainCharacterHealthHud.FEMALE;

                } else if (sexSelector.isSelected(sexP1Male.getModel())) {

                    sex = PokemonMainCharacterHealthHud.MALE;

                } else {
                    sex = PokemonMainCharacterHealthHud.NONE;
                }

                Thread th1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HUD.player1UpdateData(nameP1.getText(), Integer.parseInt(levelP1.getText()), sex);
                    }
                });

                th1.start();

                Thread th2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HUD.player1UpdateHP(Integer.parseInt(currentPsP1.getText()), Integer.parseInt(MaxPsP2.getText()));
                    }
                });
                th2.start();

                final int sex2;

                if (sexSelectorP2.isSelected(sexP2Female.getModel())) {

                    sex2 = PokemonOpponentHealthHud.FEMALE;

                } else if (sexSelectorP2.isSelected(sexP2Male.getModel())) {

                    sex2 = PokemonOpponentHealthHud.MALE;

                } else {
                    sex2 = PokemonOpponentHealthHud.NONE;
                }

                Thread th3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HUD.player2UpdateData(nameP2.getText(), Integer.parseInt(levelP2.getText()), sex2, catched.isSelected());

                    }
                });

                th3.start();

                Thread th4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HUD.player2UpdateHP(Integer.parseInt(currentPsP2.getText()), Integer.parseInt(MaxPsP2.getText()));

                    }
                });
                th4.start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                       
                        playPauseAnimations.getModel().setPressed(true);
                        HUD.playPokemonSprites();

                    }
                }).start();

            }
        }
        );

        debug.add(updateFields);

        debug.add(playPauseAnimations);

        JToggleButton changeWeather = new JToggleButton("Change weather");

        changeWeather.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.setWeather(null);
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.removeWeather();
                }
            }
        });

        debug.add(changeWeather);

        JToggleButton earthquake = new JToggleButton("Earthquake");

        earthquake.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev) {

                if (ev.getStateChange() == ItemEvent.SELECTED) {
                    HUD.startEarthquake();
                } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
                    HUD.stopEarthquake();
                }

            }

        });

        debug.add(earthquake);

        JSeparator separator4 = new JSeparator(SwingConstants.HORIZONTAL);
        separator4.setPreferredSize(new Dimension(500, 1));
        debug.add(separator4);

        JTextArea text = new JTextArea(5, 25);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        JButton sendText = new JButton("Send text");
        JButton nextText = new JButton("Next Text");

        sendText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getText() != "") {
                    HUD.addText(text.getText());
                }

                text.setText("");

            }
        });

        nextText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HUD.nextText();
            }
        });

        debug.add(text);
        debug.add(sendText);
        debug.add(nextText);

        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(500, 1));
        debug.add(separator3);

        debug.add(new JLabel("Debug mode, this program has been created by Adrián Vázquez Barrera"));

        testOutputFrame.add(HUD);

        testOutputFrame.setResizable(true);
        testOutputFrame.setSize(testOutputframeSize);

        testOutputFrame.setLocationRelativeTo(null);
        testOutputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        testOutputFrame.setSize(screenSize.width, screenSize.height + 20);

        testOutputFrame.setVisible(true);

        testInputFrame.add(debug);

        testInputFrame.setResizable(false);

        testInputFrame.setSize(testInputputframeSize);

        testInputFrame.setLocationRelativeTo(null);

        testInputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        testInputFrame.setVisible(true);

    }
}
