package be.gerdaypetgen.UI;

import be.gerdaypetgen.Main;
import static be.gerdaypetgen.Main.loadDecks;
import static be.gerdaypetgen.Main.loadWaitingWorkList;
import be.gerdaypetgen.people.Mechanic;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class WorkShopFrame extends JFrame implements ActionListener{


    private JLabel jLabelWorkShop;
    private JLabel jLabelDeck1;
    private JLabel jLabelDeck2;
    private JLabel jLabelDeck3;
    private JLabel jLabelGround;
    private JLabel jLabelVarious;
    private JLabel jLabelOffice;
    private JLabel jLabelOfficeClient;
    private JLabel jLabelOfficeAccounting;
    
    private JLabel jLabelDeck1Value;
    private JLabel jLabelDeck2Value;
    private JLabel jLabelDeck3Value;
    private JLabel jLabelGroundValue;
    private JLabel jLabelVariousValue;
    private JLabel jLabelOfficeClientValue;
    private JLabel jLabelOfficeAccountingValue;
    
    
    public JLabel jLabelTime;
    private JLabel jLabelBossAvailable;
    private JLabel jLabelLaunchBreak;
    private JLabel jLabelEveryOneHere;
    private JLabel jLabelSomeNotHere;
    
    private JCheckBox jCheckBoxBossAvailable;
    private JCheckBox jCheckBoxLaunchBreak;
    
    private JRadioButton jRadioButtonEveryoneHere;
    private JRadioButton jRadioButtonSomeNotHere;
        
    public WorkShopFrame(){
        
        initComponents();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

            if(e.getSource() instanceof JMenuItem jMenuItem){

                switch (jMenuItem.getName()) {
                    case "planificationMenuItem" ->{
                        if(Main.getCurrentUser() instanceof Mechanic){
                            Main.planificationUI();
                        }
                        break;
                    }
                    case "startMenuItem" -> {
                        
                        if(Main.getCurrentUser() instanceof Mechanic){
                            Main.startJobUI();
                        }
                        break;
                    }
                    case "endMenuItem" -> {
                        if(Main.getCurrentUser() instanceof Mechanic){
                            String[] options = {"Ground", "Deck 1", "Deck 2", "Deck 3"};
                            int x = JOptionPane.showOptionDialog(null, "Choose a deck",
                                  "End a work",
                                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                            if(x != -1){
                                Main.endDeck(x);

                            }
                        }
                        break;
                    }
                    case "overListMenuItem" -> {
                        if(Main.getCurrentUser() instanceof Mechanic){
                            Main.overListUI();
                        }
                    }
                    case "orderPartsMenuItem" -> {
                        if(Main.getCurrentUser() instanceof Mechanic){
                            Main.orderUIParts();
                        }
                    }
                    case "orderTiresMenuItem" -> {
                        if(Main.getCurrentUser() instanceof Mechanic){
                            Main.orderUITires();
                        }
                    }
                    case "orderLubricantMenuItem" -> {
                        if(Main.getCurrentUser() instanceof Mechanic){
                            Main.orderUILubricant();
                        }
                    }
                    case "systemInfoItem" -> {
                        Main.systemInfoUI();
                    }
                    case "aboutMenuItem" -> Main.aboutUI();
                    
                    default -> {
                    }
                }
            }
        
    }
    
    private void initComponents() {
        
        this.setJMenuBar(createJMenuBar());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        
        initJLabels();
        initCheckBoxes();
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(20, 20 , 20 , 20);
        gc.ipady =  gc.anchor = GridBagConstraints.CENTER;
        
        gc.weightx = 7;
        gc.weighty = 10;
        
        // FIRST COLUMN
        
        gc.gridx = 0;
        gc.gridy = 0;
        mainPanel.add(jLabelWorkShop, gc);
        
        gc.gridy = 1;
        mainPanel.add(jLabelDeck1, gc);
        
        gc.gridy = 2;
        mainPanel.add(jLabelDeck2, gc);
        
        gc.gridy = 3;
        mainPanel.add(jLabelDeck3, gc);
        
        gc.gridy = 4;
        mainPanel.add(jLabelGround, gc);
        
        gc.gridy = 5;
        mainPanel.add(jLabelVarious, gc);
        
        gc.gridy = 6;
        mainPanel.add(jLabelOffice, gc);
        
        gc.gridy = 7;
        mainPanel.add(jLabelOfficeClient, gc);
        
        gc.gridy = 8;
        mainPanel.add(jLabelOfficeAccounting, gc);
        
        gc.gridy = 8;
        mainPanel.add(jLabelOfficeAccounting, gc);
        
        // SECOND to FOURTH COLUMN
        
        gc.gridwidth = 3;
        
        gc.gridx = 1;
        gc.gridy = 1;
        mainPanel.add(jLabelDeck1Value, gc);
        
        gc.gridy = 2;
        mainPanel.add(jLabelDeck2Value, gc);
        
        gc.gridy = 3;
        mainPanel.add(jLabelDeck3Value, gc);
        
        gc.gridy = 4;
        mainPanel.add(jLabelGroundValue, gc);
        
        gc.gridy = 5;
        mainPanel.add(jLabelVariousValue, gc);
        
        gc.gridy = 7;
        mainPanel.add(jLabelOfficeClientValue, gc);
       
        gc.gridy = 8;
        mainPanel.add(jLabelOfficeAccountingValue, gc);
        
        // FIFTH COLUMN
        gc.gridwidth = 1;

        gc.gridx = 4;
        gc.gridy = 0;
        mainPanel.add(Box.createHorizontalStrut(150), gc);
        
        // SIXTH COLUMN
        
        gc.gridx = 5;
        gc.gridy = 0;
        mainPanel.add(Box.createHorizontalBox(), gc);
        
        gc.gridy = 1;
        gc.gridwidth = 2;
        gc.gridheight = 5;
                      
        try {
            
            BufferedImage image = ImageIO.read(new File("src/resources/repairCar.jpg"));
            Image newImage = image.getScaledInstance(530, 310, Image.SCALE_DEFAULT);
            ImageIcon imageIcon = new ImageIcon(newImage);
            JLabel imageLabel = new JLabel(imageIcon);            
            mainPanel.add(imageLabel, gc);
            
        }catch(IOException e){
            System.out.printf("Error: Impossible to load repairCar.jpg : %s", e.getMessage());
        }
       
        gc.gridwidth = 1;
        gc.gridheight = 1;

        gc.gridy = 7;
        JPanel jPanelBoss = new JPanel();
        jPanelBoss.setLayout(new FlowLayout(FlowLayout.LEFT));
      
        jPanelBoss.add(jCheckBoxBossAvailable);
        jPanelBoss.add(jLabelBossAvailable);
        
        mainPanel.add(jPanelBoss, gc);
        
        gc.gridy = 8;
        JPanel jPanelLaunch = new JPanel();
        jPanelLaunch.setLayout(new FlowLayout(FlowLayout.LEFT));
      
        jPanelLaunch.add(jCheckBoxLaunchBreak);
        jPanelLaunch.add(jLabelLaunchBreak);
        
        mainPanel.add(jPanelLaunch, gc);        
        // SEVENTH COLUMN
        
        gc.gridx = 6;
        gc.gridy = 0;
        mainPanel.add(jLabelTime, gc);
        
        gc.gridy = 7;
        JPanel jPanelEveryoneHere = new JPanel();
        jPanelEveryoneHere.setLayout(new FlowLayout(FlowLayout.LEFT));
      
        jPanelEveryoneHere.add(jRadioButtonEveryoneHere);
        jPanelEveryoneHere.add(jLabelEveryOneHere);
        
        mainPanel.add(jPanelEveryoneHere, gc);   
        
        gc.gridy = 8;
        JPanel jPanelSomeNotHere = new JPanel();
        jPanelSomeNotHere.setLayout(new FlowLayout(FlowLayout.LEFT));
      
        jPanelSomeNotHere.add(jRadioButtonSomeNotHere);
        jPanelSomeNotHere.add(jLabelSomeNotHere);
        
        mainPanel.add(jPanelSomeNotHere, gc);   
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButtonEveryoneHere);
        buttonGroup.add(jRadioButtonSomeNotHere);
        
        this.add(mainPanel);
    }
    
    private void initJLabels(){
        
        jLabelWorkShop = createTitle("WorkShop");
        jLabelOffice = createTitle("Office");

        jLabelDeck1 = new JLabel("Deck 1 :");
        jLabelDeck2 = new JLabel("Deck 2 :");
        jLabelDeck3 = new JLabel("Deck 3 :");
        jLabelGround = new JLabel("Ground :");
        jLabelVarious = new JLabel("Various :");
        jLabelOfficeClient = new JLabel("Client office :");
        jLabelOfficeAccounting = new JLabel("Accounting office :");
        
        String abailable = "Available";
        jLabelDeck1Value = createColoredValue(abailable, Color.YELLOW);
        jLabelDeck2Value = createColoredValue(abailable, Color.MAGENTA);
        jLabelDeck3Value = createColoredValue(abailable, Color.GREEN);
        jLabelGroundValue = createColoredValue(abailable, Color.YELLOW);
        jLabelVariousValue = createColoredValue("Attention ! Accident sur l'autoroute !", Color.WHITE);
        jLabelOfficeClientValue = createColoredValue("-- libre --", Color.MAGENTA);
        
        jLabelOfficeAccountingValue = new JLabel("Me Boulier est là");
        jLabelTime = new JLabel();
        jLabelBossAvailable = new JLabel("Boss availibility");
        jLabelLaunchBreak = new JLabel("Launch break");
        jLabelEveryOneHere = new JLabel("Everyone is here");
        jLabelSomeNotHere = new JLabel("Some absent");
        
    }
    
    private void initCheckBoxes(){
        jCheckBoxBossAvailable = new JCheckBox();
        jCheckBoxLaunchBreak = new JCheckBox();
        
        jRadioButtonEveryoneHere = new JRadioButton();
        jRadioButtonSomeNotHere = new JRadioButton();
    }
    
    private JMenuBar createJMenuBar(){
        
        JMenuBar jMenuBar = new JMenuBar();
        
        JMenu jMenuWorkShop = new JMenu("WorkShop");
        
        JMenuItem jMenuItemToPlan = new JMenuItem("Planification");
        jMenuItemToPlan.setName("planificationMenuItem");
        jMenuItemToPlan.addActionListener(this);
        
        JMenuItem jMenuItemCoverage = new JMenuItem("Start a work");
        jMenuItemCoverage.setName("startMenuItem");
        jMenuItemCoverage.addActionListener(this);
        
        JMenuItem jMenuItemEnd = new JMenuItem("End a work");
        jMenuItemEnd.setName("endMenuItem");
        jMenuItemEnd.addActionListener(this);

        JMenuItem jMenuItemOverList = new JMenuItem("Over list");
        jMenuItemOverList.setName("overListMenuItem");
        jMenuItemOverList.addActionListener(this);
        
        jMenuWorkShop.add(jMenuItemToPlan);
        jMenuWorkShop.add(jMenuItemCoverage);
        jMenuWorkShop.add(jMenuItemEnd);
        jMenuWorkShop.addSeparator();
        jMenuWorkShop.add(jMenuItemOverList);
        
        
        JMenu jMenuMaterial = new JMenu("Material");
        
        JMenuItem orderJMenu = new JMenu("Order");
        
        
        JMenuItem orderPartsJMenuItem = new JMenuItem("Order parts");
        JMenuItem orderTiresJMenuItem = new JMenuItem("Order tires");
        JMenuItem orderLubricantJMenuItem = new JMenuItem("Order lubricant");

        orderPartsJMenuItem.setName("orderPartsMenuItem");
        orderPartsJMenuItem.addActionListener(this);
        
        orderTiresJMenuItem.setName("orderTiresMenuItem");
        orderTiresJMenuItem.addActionListener(this);
        
        orderLubricantJMenuItem.setName("orderLubricantMenuItem");
        orderLubricantJMenuItem.addActionListener(this);
        
        
        orderJMenu.add(orderPartsJMenuItem);
        orderJMenu.add(orderTiresJMenuItem);
        orderJMenu.add(orderLubricantJMenuItem);
        
        jMenuMaterial.add(orderJMenu);

        JMenu jMenuCustomers = new JMenu("Customers");
        
        JMenu jMenuBills = new JMenu("Bills");
        
        JMenu jMenuOptions = new JMenu("Options");
        
        JMenuItem jMenuItemSystemInfo = new JMenuItem("System info");
        jMenuItemSystemInfo.addActionListener(this);
        jMenuItemSystemInfo.setName("systemInfoItem");
        jMenuOptions.add(jMenuItemSystemInfo);

        JMenu jMenuHelp = new JMenu("Help");
        
        JMenuItem jMenuItemToStart = new JMenuItem("To start");
        JMenuItem jMenuItemAbout = new JMenuItem("About"); 
        
        jMenuItemAbout.setName("aboutMenuItem");
        jMenuItemAbout.addActionListener(this);
        jMenuHelp.add(jMenuItemToStart);
        jMenuHelp.add(jMenuItemAbout);
        
        jMenuBar.add(jMenuWorkShop);
        jMenuBar.add(jMenuMaterial);
        jMenuBar.add(jMenuCustomers);
        jMenuBar.add(jMenuBills);
        jMenuBar.add(Box.createHorizontalGlue());
        jMenuBar.add(jMenuOptions);
        jMenuBar.add(jMenuHelp);
      
        return jMenuBar;
    }
    
    private JLabel createTitle(String text){
        
        JLabel title = new JLabel(text);
        
        title.setFont(new Font(title.getFont().getName(), Font.BOLD ,title.getFont().getSize() + 2));
        
        return title;
    }
    
    private JLabel createColoredValue(String text, Color color){
        
        JLabel coloredValue = new JLabel(text);
        coloredValue.setOpaque(true);
        coloredValue.setBackground(color);
        return coloredValue;
    }
    
    public void changeDeck1(String s){
        jLabelDeck1Value.setText(s);
    }
    
    public void changeDeck2(String s){
        jLabelDeck2Value.setText(s);
    }
    
    public void changeDeck3(String s){
        jLabelDeck3Value.setText(s);
    }
    
    public void changeGround(String s){
        jLabelGroundValue.setText(s);
    }
}
