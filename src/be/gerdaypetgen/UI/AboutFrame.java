package be.gerdaypetgen.UI;

import be.gerdaypetgen.Main;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sacha
 */
public class AboutFrame extends JFrame implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof JButton jButton){
            
            if(jButton.getName().equals("okButton")){
                
                Main.closeAboutUI();
                
            }
        }
    }
    
    public AboutFrame(){
        initComponents();
    }
    
    
    private void initComponents(){
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 30, 30));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel namePanel = new JPanel();
        JLabel name = new JLabel("Application \"WorkShop HEPL\"");
        namePanel.add(name);
        
        JPanel madeByPanel = new JPanel();
        JLabel madeBy = new JLabel("Made by Petgen Sacha and Gerday Léandre");
        madeByPanel.add(madeBy);
        
        JPanel panelRadio = new JPanel();
        
        JRadioButton like = new JRadioButton("Like");
        JRadioButton medium = new JRadioButton("Medium");
        JRadioButton hate = new JRadioButton("Dislike");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(like);
        bg.add(medium);
        bg.add(hate);
        
        panelRadio.add(like);
        panelRadio.add(medium);
        panelRadio.add(hate);
        
        JButton okButton = new JButton("Ok");
        okButton.setName("okButton");
        okButton.addActionListener(this);
        
        panel.add(namePanel);
        
        panel.add(madeByPanel);

        panel.add(panelRadio);
        panel.add(okButton);
        
        this.add(panel);
    }
    
}
