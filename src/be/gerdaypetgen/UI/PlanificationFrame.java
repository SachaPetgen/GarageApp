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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class PlanificationFrame extends JFrame implements ActionListener{
       
    private JTextField jTextFieldCarType;
    private JTextField jTextFieldNumberPlate;
    private JComboBox<String> jListOwners;
    private JComboBox<String> jListWorkType;
    private JTextArea jTextAreaParticularInfo;
    
    private JRadioButton jRadioButtonMaintenance;
    private JRadioButton jRadioButtonRepair;
    private ButtonGroup bg;
            
    public PlanificationFrame() {
        
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        

        if(e.getSource() instanceof JButton jButton){
            
            if(jButton.getName().equals("okButton")){
                
                if(Main.getCurrentUser() instanceof Mechanic && !jTextFieldCarType.getText().equals("") && !jTextFieldNumberPlate.getText().equals("")){
                    
                    CarType carType = new CarType(jTextFieldCarType.getText());
                    
                    Client client = new Client(String.valueOf(jListOwners.getSelectedItem()), "Noé", "4500 Huy", "0496555555");
                    
                    Car car = new Car(carType, client);
                            
                    Work work;
                    
                    Mechanic responsibleMechanic = (Mechanic) Main.getCurrentUser();
                    
                    if(jRadioButtonMaintenance.isSelected()){
                        work = new Maintenance( responsibleMechanic  , car);
                    }
                    else{
                        work = new Repair( responsibleMechanic , car);
                    }
                    
                    //work.add(jTextFieldNumberPlate.getText());
                    //work.add(String.valueOf(jListWorkType.getSelectedItem()));
                    //work.add(jTextAreaParticularInfo.getText()); 

                    Main.closePlanificationUI(work);
                }
                
            }
            else if(jButton.getName().equals("cancelButton")){
                Main.closePlanificationUI(null);
            }

        }

    }

    private void initComponents(){
        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3, 30, 30));  
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel jLabelCarType = new JLabel("Car type :");
        jTextFieldCarType = new JTextField();
        
        
        JLabel jLabelNumberPlate = new JLabel("Number Plate :");
        jTextFieldNumberPlate = new JTextField();
        
        JPanel platePanel = new JPanel();
        platePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JCheckBox jCheckBoxBelgiumPlate = new JCheckBox();
        JLabel jLabelBelgiumPlate = new JLabel("Belgium Plate");
                
        JLabel jLabelOwner = new JLabel("Owner :");
        
        String[] listOwners = {"M. Dieu", "Mme. Dupont", "M. Lacroix", "M. Petgen", "M.Gerday"};
        jListOwners = new JComboBox<>(listOwners);
        
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JCheckBox jCheckBoxNew = new JCheckBox();
        JLabel jLabelNew = new JLabel("new !");
              
        JPanel maintenancePanel = new JPanel();
        maintenancePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jRadioButtonMaintenance = new JRadioButton();
        JLabel jLabelMaintenance = new JLabel("Maintenance");
        
        JPanel repairPanel = new JPanel();
        repairPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jRadioButtonRepair = new JRadioButton();
        JLabel jLabelRepair = new JLabel("Repair");
        
        bg = new ButtonGroup();
        bg.add(jRadioButtonMaintenance);
        bg.add(jRadioButtonRepair);

        JLabel jLabelWorkType = new JLabel("Work type :");
        
        String[] listWorkType = {"Winter Check", "Engine Check", "Flat tire", "Drain"};
        jListWorkType  = new JComboBox<>(listWorkType);
        
        JLabel jLabelParticularInfo = new JLabel("Particular informations :");
        jTextAreaParticularInfo = new JTextArea();
        
        JButton jButtonOk = new JButton("Ok");
        jButtonOk.setName("okButton");
        jButtonOk.addActionListener(this);
        JButton jButtonCancel = new JButton("Cancel");
        jButtonCancel.setName("cancelButton");
        jButtonCancel.addActionListener(this);

        mainPanel.add(jLabelCarType);
        mainPanel.add(jTextFieldCarType);
        mainPanel.add(Box.createRigidArea(null));
        
        mainPanel.add(jLabelNumberPlate);
        mainPanel.add(jTextFieldNumberPlate);
        platePanel.add(jCheckBoxBelgiumPlate);
        platePanel.add(jLabelBelgiumPlate);
        mainPanel.add(platePanel);
        
        mainPanel.add(jLabelOwner);
        mainPanel.add(jListOwners);
        newPanel.add(jCheckBoxNew);
        newPanel.add(jLabelNew);
        mainPanel.add(newPanel);
        
        maintenancePanel.add(jRadioButtonMaintenance);
        maintenancePanel.add(jLabelMaintenance);
        mainPanel.add(maintenancePanel);
                
        repairPanel.add(jRadioButtonRepair);
        repairPanel.add(jLabelRepair);
        mainPanel.add(repairPanel);
        
        mainPanel.add(Box.createRigidArea(null));

        mainPanel.add(jLabelWorkType);
        mainPanel.add(jListWorkType);
        mainPanel.add(Box.createRigidArea(null));
        
        mainPanel.add(jLabelParticularInfo);
        mainPanel.add(jTextAreaParticularInfo);
        mainPanel.add(Box.createRigidArea(null));

        mainPanel.add(jButtonOk);
        mainPanel.add(Box.createRigidArea(null));
        mainPanel.add(jButtonCancel);
        
        this.add(mainPanel);
        
    }
}
