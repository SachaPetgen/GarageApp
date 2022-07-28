package be.gerdaypetgen.UI;

import be.gerdaypetgen.Main;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
public class SystemInfoFrame extends JFrame implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof JButton jButton){
            
            if(jButton.getName().equals("okButton")){
                
                Main.closeSystemInfoUI();
                
            }
        }
    }
    
    public SystemInfoFrame(){
        initComponents();
    }
    
    
    private void initComponents(){
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        List<String> logList;
        
        if(Main.logFile.getNumberOfRows() > 30){
            logList = Main.logFile.listRows().subList(Main.logFile.getNumberOfRows() - 30, Main.logFile.getNumberOfRows());
        }
        else{
            logList = Main.logFile.listRows();
        }

        for(String s : logList){
            JLabel jLabel = new JLabel(s);
            panel.add(jLabel);
        }   
        
        JPanel centerButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("Ok");
        okButton.setName("okButton");
        okButton.addActionListener(this);

        centerButton.add(okButton);
        
        panel.add(centerButton);
        
        this.add(panel);
    }
    
}
