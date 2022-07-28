package be.gerdaypetgen.UI;

import be.gerdaypetgen.Main;
import be.gerdaypetgen.activity.Maintenance;
import be.gerdaypetgen.activity.Repair;
import be.gerdaypetgen.activity.Work;
import be.gerdaypetgen.people.Client;
import be.gerdaypetgen.people.Mechanic;
import be.gerdaypetgen.vehicles.Car;
import be.gerdaypetgen.vehicles.CarType;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OverListFrame extends JFrame implements ActionListener{
    
    private JTable overListJTable;
    
    public OverListFrame() {
        
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof JButton jButton){

            if(jButton.getName().equals("backButton")){
                
                Main.closeOverListUI();
            }
        }
    }
    
    private void initComponents(){
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 0, 30, 30));  
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel overListPanel = new JPanel();
        overListPanel.setLayout(new GridLayout(2,0));
        
        String[] tableTitles = {"Car type", "Plate number", "Owner", "Work", "Work Type", "Additionals informations"};
        
        overListJTable = new JTable();
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(tableTitles);
        overListJTable.setModel(model);
        
        for (Work work : Main.getOverList()) 
        {
            Vector<String> workToString = new Vector<>();

            if(work.getVehicle() instanceof Car){
                Car car = (Car) work.getVehicle();
                workToString.add(car.getType().toString());
            }
            
            workToString.add("00000"); // Number Plate TO DYNAMISE
            workToString.add(work.getVehicle().getClient().getName());
            
            if(work instanceof Repair){
                workToString.add("Repair");
            }
            else{
                workToString.add("Maintenance");
            }
            
            workToString.add("TDB"); // Work
            workToString.add("TDB"); // Particular Informations TO DYNAMISE
            model.addRow(workToString);
            
        }
        
        overListJTable.setFillsViewportHeight(true);
        
        JScrollPane scrollPane =new JScrollPane(overListJTable);  
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        overListPanel.add(scrollPane);
  
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton backJButton = new JButton("Back");
        backJButton.addActionListener(this);
        backJButton.setName("backButton");

        buttonPanel.add(backJButton);
        
        JLabel titleJLabel = new JLabel("List of work done :");
        titleJLabel.setFont(new Font(titleJLabel.getFont().getName(), Font.BOLD ,titleJLabel.getFont().getSize() + 2));
        mainPanel.add(titleJLabel);
        mainPanel.add(overListPanel);
        mainPanel.add(buttonPanel);
        
        this.add(mainPanel);
        
        
    }

}
