package be.gerdaypetgen.UI;

import be.gerdaypetgen.Main;
import be.gerdaypetgen.people.GarageStaff;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame implements ActionListener{
    
    private JTextField jTextFieldId;
    private JPasswordField jTextFieldPass;
    private JLabel jLabelId;
    private JLabel jLabelPass;
    private JButton jButtonLogin;
    private JButton jButtonCancel;
    private JRadioButton jRadioButtonMember;
    private JRadioButton jRadioButtonOutsideMember;
    private ButtonGroup buttonGroup;
        
   
    public LoginFrame(){
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                
        if(e.getSource() instanceof JButton jButton){       
            
            if(jButton.getName().equals("loginButton")){
             
                if(!jTextFieldId.getText().equals("")){
                    
                    String id = jTextFieldId.getText();
                    
                    if(GarageStaff.getMemberById(id) != null){
                        
                        GarageStaff member = GarageStaff.getMemberById(id);
                        
                        if(!jTextFieldPass.getPassword().equals("")){
                            
                            char[] pass = jTextFieldPass.getPassword() ;
                            
                            if(member.validate(pass)){
                                
                                JOptionPane.showMessageDialog(this, "Successfully connected !");
                                
                                Main.closeLoginUI();
                                Main.workShopUI();
                                Main.setCurrentUser(member);
                                
                            }
                            else{
                                
                                JOptionPane.showMessageDialog(this, "Password incorrect !", "Error" , JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Please enter a password !", "Error" , JOptionPane.ERROR_MESSAGE);

                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Unknown user !", "Error" , JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Please enter an id !", "Error" , JOptionPane.ERROR_MESSAGE);

                }
            }
            else if(jButton.getName().equals("cancelButton")){
                
                System.exit(0);
            }
        }
    }
    
    private void initComponents() {
        
        JPanel panel = new JPanel();  
        panel.setLayout(new GridLayout(0, 2, 20, 20));  
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        jLabelId = new JLabel();
        jLabelId.setText("Id :");
        jLabelPass = new JLabel();
        jLabelPass.setText("Password :");

        
        jTextFieldId = new JTextField();
        jTextFieldPass = new JPasswordField();
        
        jRadioButtonMember = new JRadioButton();
        jRadioButtonMember.setText("Staff member");
        jRadioButtonOutsideMember = new JRadioButton();
        jRadioButtonOutsideMember.setText("Outside authorize");
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButtonMember);
        buttonGroup.add(jRadioButtonOutsideMember);
        
        
        jButtonLogin = new JButton();
        jButtonLogin.setText("Login");
        jButtonLogin.setName("loginButton");
        jButtonLogin.addActionListener(this);
        jButtonCancel = new JButton();
        jButtonCancel.setText("Cancel");
        jButtonCancel.setName("cancelButton");
        jButtonCancel.addActionListener(this);

        
        panel.add(jLabelId);
        panel.add(jTextFieldId);
        
        panel.add(jLabelPass);
        panel.add(jTextFieldPass);
        
        panel.add(jRadioButtonMember);
        panel.add(jRadioButtonOutsideMember);
        
        panel.add(jButtonLogin);
        panel.add(jButtonCancel);
        
        this.add(panel);
        
    }
}
