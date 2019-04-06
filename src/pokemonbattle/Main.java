package pokemonbattle;

import texttospeech.Narrator;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) {
        
        Narrator.getInstance().setLanguage(Narrator.Languages.ITALIAN);
                
        Narrator.getInstance().speak("buongiorno, sono una voce artificiale.");
                Narrator.getInstance().speak("buongiorno, sono una voce artificiale.");

        
//       Narrator.getInstance().speak("Después de tanto esfuerzo, creo que he conseguido dar con"
//               + "una buena voz en español. Se llama Lucía y es una modificación de la versión italiana de la que procede. "
//               + "Esta voz ha sido modificada por Adrián Vázquez Barrera.");
//     
//               
//       Narrator.getInstance().speak("Después de tanto esfuerzo, creo que he conseguido dar con"
//               + "una buena voz en español. Se llama Lucía y es una modificación de la versión italiana de la que procede. "
//               + "Esta voz ha sido modificada por Adrián Vázquez Barrera.");
//     

        //debug.PokemonBattleGraphicControlerDebug.test();
//        JFrame frame = new JFrame();
//        
//        
//        JButton jb = new JButton("Button");
//        
//        
//        
//        jb.getModel().setMnemonic(KeyEvent.VK_1);
//        
//        jb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Hola");
//            }
//        });
//        
//        
//        frame.add(jb);
//        frame.setSize(300, 300);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
    }

}
