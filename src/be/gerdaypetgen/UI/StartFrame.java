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

public class StartFrame extends JFrame implements ActionListener{
    
    private JTable waitingWorkJTable;
    private ButtonGroup bg;
    private JComboBox<Integer> jDeckComboBox;
    private JRadioButton deckRadioButton;
    private JRadioButton groundRadioButton;
    
    public StartFrame() {
        
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof JButton jButton){

            if(jButton.getName().equals("okButton")){
                
                if(waitingWorkJTable.getSelectedRow() >= 0){
                    Work work = Main.getWaitingWorkList().get(waitingWorkJTable.getSelectedRow());

                    boolean available = false;

                    if(deckRadioButton.isSelected()){
                        int index = (int) jDeckComboBox.getSelectedItem();
                        available = Main.setDeck(index, work);
                    }
                    else {
                        available = Main.setDeck(0, work);
                    }

                    if(available == false){
                        JOptionPane.showMessageDialog(this, "Deck unavailable", "Error" , JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        Main.removeWorkFromWaitingList(work);
                        Main.saveDecks();
                        JOptionPane.showMessageDialog(this, "Job started !", "Success" , JOptionPane.INFORMATION_MESSAGE);

                    }

                    Main.closeStartJobUI();
                }
                
            }
            else if(jButton.getName().equals("cancelButton")){
                Main.closeStartJobUI();
            }

        }

    }
    
    private void initComponents(){
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 0, 30, 30));  
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel waitingWorkPanel = new JPanel();
        waitingWorkPanel.setLayout(new GridLayout(2,0));
        
        String[] tableTitles = {"Car type", "Plate number", "Owner", "Work", "Work Type", "Additionals informations"};
        
        waitingWorkJTable = new JTable();
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(tableTitles);
        waitingWorkJTable.setModel(model);
        
        for (Work work : Main.getWaitingWorkList()) 
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
        
        waitingWorkJTable.setFillsViewportHeight(true);
        
        JScrollPane scrollPane =new JScrollPane(waitingWorkJTable);  
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        waitingWorkPanel.add(scrollPane);
                
        JPanel chooseLocationPanel = new JPanel();
        chooseLocationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        deckRadioButton = new JRadioButton("Deck");
        
        Integer[] deckList = {1,2,3};
         jDeckComboBox = new JComboBox<>(deckList);
        
        groundRadioButton = new JRadioButton("Ground");
        
        bg = new ButtonGroup();
        bg.add(deckRadioButton);
        bg.add(groundRadioButton);
        
        chooseLocationPanel.add(deckRadioButton);
        chooseLocationPanel.add(jDeckComboBox);
        chooseLocationPanel.add(Box.createHorizontalStrut(100));
        chooseLocationPanel.add(groundRadioButton);
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton okJButton = new JButton("Ok");
        okJButton.addActionListener(this);
        okJButton.setName("okButton");
        JButton cancelJButton = new JButton("Cancel");
        cancelJButton.addActionListener(this);
        cancelJButton.setName("cancelButton");

        buttonPanel.add(okJButton);
        buttonPanel.add(cancelJButton);
        
        JLabel titleJLabel = new JLabel("List of waiting works :");
        titleJLabel.setFont(new Font(titleJLabel.getFont().getName(), Font.BOLD ,titleJLabel.getFont().getSize() + 2));
        mainPanel.add(titleJLabel);
        mainPanel.add(waitingWorkPanel);
        mainPanel.add(chooseLocationPanel);
        mainPanel.add(buttonPanel);
        
        this.add(mainPanel);
        
        
    }

}
