package be.gerdaypetgen.UI;

import be.gerdaypetgen.Main;
import be.gerdaypetgen.activity.Order;
import be.gerdaypetgen.activity.OrderPriorityEnum;
import be.gerdaypetgen.activity.Response;
import be.gerdaypetgen.people.Mechanic;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;

public class OrderFrame extends JFrame implements ActionListener{

    private ButtonGroup bg;
            
    private JTextField nameJTextField;
    private JTextField typeJTextField;
    private JTextField quantityJTextField;
    public DefaultTableModel model;
    
    int orderType;

    public OrderFrame(int orderType){
        
        this.orderType = orderType;
        initComponents();

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton jButton){

                switch (jButton.getName()) {
                    case "sendButton" ->{

                        if(Main.getCurrentUser() instanceof Mechanic){
                            
                            OrderPriorityEnum orderPriority = OrderPriorityEnum.NOT_PRIORITIZE;
                            
                            switch(bg.getSelection().getActionCommand()){
                                case "Urgent" -> orderPriority = OrderPriorityEnum.URGENT;
                                case "Normal" -> orderPriority = OrderPriorityEnum.NORMAL;
                            }
                            
                            String name = nameJTextField.getText();
                            String type = typeJTextField.getText();
                            String quantityString = quantityJTextField.getText();
                            
                            int quantity = Integer.parseInt(quantityString);
                            
                            if(quantity > 0){
                                
                                Order order = new Order(name, type, quantity, orderPriority);
                                
                                switch(orderType){
                                    case 0:
                                            Main.closeOrderUIParts(order);
                                            break;
                                    case 1:
                                            Main.closeOrderUITires(order);
                                            break;
                                    case 2:
                                            Main.closeOrderUILubricant(order);
                                            break;
                                }
                                
                            }

                        }
                        
                        break;
                    }
                    case "cancelButton" -> {
                        switch(orderType){
                        case 0:
                            Main.closeOrderUIParts(null);
                            break;
                        case 1:
                            Main.closeOrderUITires(null);
                            break;
                        case 2:
                            Main.closeOrderUILubricant(null);
                            break;
                        }
                    }
                    default -> {
                            
                    }
                }
            }
        
    }
   
    private void initComponents() {
        
        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridLayout(4, 0, 30, 15));  
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
       
      
        JLabel orderJLabel = new JLabel("Order :");
        JLabel timeJLabel = new JLabel("19/3/2017");
        
        
        JRadioButton urgentJRadioButton = new JRadioButton("Urgent");
        urgentJRadioButton.setActionCommand("Urgent");
        JRadioButton normalJRadioButton = new JRadioButton("Normal");
        normalJRadioButton.setActionCommand("Normal");
        JRadioButton noPriorityJRadioButton = new JRadioButton("No priority");
        noPriorityJRadioButton.setActionCommand("No Priority");
        
        bg = new ButtonGroup();
        bg.add(urgentJRadioButton);
        bg.add(normalJRadioButton);
        bg.add(noPriorityJRadioButton);
        
        JLabel titleJLabel = new JLabel("Name:");
        JLabel typeJLabel = new JLabel("Type:");
        JLabel quantityJLabel = new JLabel("Quantity:");
               
        nameJTextField = new JTextField();
        typeJTextField = new JTextField();
        quantityJTextField = new JTextField();
       
        String[] tableTitles = {"Name", "Type", "Quantity", "Availibity", "Expedition Date"};

        model = new DefaultTableModel();
        model.setColumnIdentifiers(tableTitles);
                
        ArrayList<Response> list = null;
        
        switch(orderType){
            case 0:
                list = Main.orderDoneListParts;
                break;
            case 1:
                list = Main.orderDoneListTires;
                break;
            case 2:
                list = Main.orderDoneListLubricant;
                break;
        }        
        
        String [] row = new String[5];
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
                
        for(Response resp : list){
            row[0] = resp.getOrder().getName();
            row[1] = resp.getOrder().getType();
            row[2] = String.valueOf(resp.getOrder().getQuantity());
            row[3] = resp.isAvailable()? "Available" : "Not available";;
            row[4] = dtf.format(resp.getExpeditionDate());
            model.addRow(row);
        }

        JTable orderJTable = new JTable(model);
        Dimension size = new Dimension(300,500);
        orderJTable.setMaximumSize(size);
        orderJTable.setPreferredSize(size);
        orderJTable.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(orderJTable);  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        JButton sendJButton = new JButton("Send");
        sendJButton.addActionListener(this);
        sendJButton.setName("sendButton");
        JButton cancelJButton = new JButton("Cancel");
        cancelJButton.addActionListener(this);
        cancelJButton.setName("cancelButton");

        JPanel rowPanel1 = new JPanel();
        rowPanel1.setLayout(new BoxLayout(rowPanel1, BoxLayout.LINE_AXIS));
        rowPanel1.add(orderJLabel);
        rowPanel1.add(Box.createHorizontalGlue());
        rowPanel1.add(timeJLabel);
        
        JPanel rowPanel2 = new JPanel();
        rowPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
        rowPanel2.add(urgentJRadioButton);
        rowPanel2.add(normalJRadioButton);
        rowPanel2.add(noPriorityJRadioButton);
        
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.LINE_AXIS));
                
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(3,2,10,10));
        
        fieldPanel.add(titleJLabel);
        fieldPanel.add(nameJTextField);
        
        fieldPanel.add(typeJLabel);
        fieldPanel.add(typeJTextField);
        
        fieldPanel.add(quantityJLabel);
        fieldPanel.add(quantityJTextField);
                        
        middlePanel.add(fieldPanel);
        middlePanel.add(Box.createHorizontalStrut(100));
        middlePanel.add(scrollPane);
        
        JPanel rowPanel4 = new JPanel();
        rowPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        rowPanel4.add(sendJButton);
        rowPanel4.add(cancelJButton);
        
        mainPanel.add(rowPanel1);
        mainPanel.add(rowPanel2);
        mainPanel.add(middlePanel);
        mainPanel.add(rowPanel4);

        this.add(mainPanel);
    }

}

