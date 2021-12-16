/***************************************************/
/*    https://www.guru99.com/java-swing-gui.html   */
/***************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Form {

    private static JFrame form;
    private static JTextArea ta;
    private static JPanel panel;
    private static JButton yes;
    private static JButton no;

/****************************************************/
/**************         BUILD      ******************/
/****************************************************/
/****************************************************/
    public Form(NodeMap map) {
        createFrame(map);
        nodeDisplay(map);
    }

    private static void createFrame(NodeMap map){
        form = new JFrame("Perec");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setSize(600,200);
        // form.setResizable(false); //Disable the Resize Button
        form.setLocationRelativeTo(null);

        createPanel();
        createButtons(map);
        createTextArea();

        panel.add(yes);
        panel.add(no);
        //Adding Components to the frame.
        form.getContentPane().add(BorderLayout.SOUTH, panel);
        form.getContentPane().add(BorderLayout.CENTER, ta);
        form.setVisible(true);


    }

    private static void createPanel(){
        panel = new JPanel(); // the panel is not visible in output
        setColors(panel, Color.BLACK,Color.white);
    }

    private static void createButtons(NodeMap map){
        yes = new JButton("Yes");
        yes.setFont(createFont(50));
        setColors(yes,Color.darkGray, Color.green);
        yes.addActionListener((ActionEvent e) -> {
            move(map,1);
            nodeDisplay(map);
        });

        no = new JButton("No");
        no.setFont(createFont(50));
        setColors(no,Color.darkGray, Color.red);
        no.addActionListener((ActionEvent e) -> {
            move(map,2);
            nodeDisplay(map);
        });
    }


    private static void createTextArea(){
        ta = new JTextArea();
        ta.setFont(createFont(32));
        ta.setBackground(Color.BLACK);
        ta.setForeground(Color.yellow);
    }

/****************************************************/
/**************      NAVIGATE       *****************/
/****************************************************/
/****************************************************/
    private static void move(NodeMap map, int direction){
        if (map.currentNode().getQuestion().equals("-"))
        {
            map.noDecision(); }
        else {
            map.decision(direction); }
    }

    private static void nodeDisplay(NodeMap map){
        ta.setText( map.currentNode().getDescription() + "\n");
        ta.append(map.currentNode().getQuestion() + "\n");
        ta.setLineWrap(true);
        if (map.currentNode().getQuestion().equals("-")){
            yes.setFont(createFont(40));
            yes.setText("pressEnterToContinue");
            no.setVisible(false);
        }
        else {
            no.setVisible(true);
            yes.setFont(createFont(50));
            yes.setText("Yes");
        }
    }



/***************************************************/
/*                      HELPERS                    */
/***************************************************/

    private static void setColors(JComponent object, Color bg, Color fg){
        object.setBackground(bg);
        object.setForeground(fg);
    }
    private static Font createFont(int size){
        return  new Font("Courier", Font.PLAIN,size);
        //return  new Font("Atlantis International", Font.PLAIN,size);
    }
}
