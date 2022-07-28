package be.gerdaypetgen;

import be.gerdaypetgen.UI.AboutFrame;
import be.gerdaypetgen.UI.LoginFrame;
import be.gerdaypetgen.UI.OrderFrame;
import be.gerdaypetgen.UI.OverListFrame;
import be.gerdaypetgen.UI.PlanificationFrame;
import be.gerdaypetgen.UI.StartFrame;
import be.gerdaypetgen.UI.SystemInfoFrame;
import be.gerdaypetgen.UI.WorkShopFrame;
import be.gerdaypetgen.activity.Order;
import be.gerdaypetgen.activity.OrderPriorityEnum;
import be.gerdaypetgen.activity.Work;
import be.gerdaypetgen.activity.Response;
import be.gerdaypetgen.people.Employee;
import be.gerdaypetgen.people.GarageStaff;
import be.gerdaypetgen.people.MechSpecEnum;
import be.gerdaypetgen.people.Mechanic;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import network.NetworkBasicClient;

public class Main {
    
    private static Clock clock;
    
    private static NetworkBasicClient clientParts;
    private static NetworkBasicClient clientTires;
    private static NetworkBasicClient clientLubricant;

    public static LogFile logFile = new LogFile("logs.txt");
    
    private static ArrayList<Work> waitingWorkList = new ArrayList<>();
    private static ArrayList<Work> overList = new ArrayList<>();
    private static ArrayList<Work> decks = new ArrayList<>();
    
    public static ArrayList<Response> orderDoneListParts = new ArrayList<>();
    public static ArrayList<Response> orderDoneListTires = new ArrayList<>();
    public static ArrayList<Response> orderDoneListLubricant = new ArrayList<>();

        
    private final static ArrayList<GarageStaff> users = new ArrayList<>();
    private static GarageStaff currentUser;
        
    static{ 
        Employee emp = new Employee("Dupont", "Crhistine", "4500 Huy", "0496555555", "emp");
        Mechanic mech = new Mechanic("Lacroix", "Thomas", "4520 Wanze", "0496999999", "mech", MechSpecEnum.MOTORIZATION);
        
        users.add(emp);
        users.add(mech);
        
    }
       
    private static LoginFrame loginFrame;
    private static WorkShopFrame workShopFrame;
    
    private static PlanificationFrame toPlanFrame;
    private static StartFrame startFrame;
    private static OverListFrame overListFrame;
    
    private static OrderFrame orderFrameParts;
    private static OrderFrame orderFrameTires;
    private static OrderFrame orderFrameLubricant;
    
    private static SystemInfoFrame systemInfoFrame;

    private static AboutFrame aboutFrame;

    public static void main(String args[]){
                
        try{
             UIManager.setLookAndFeel(new NimbusLookAndFeel());

        }
        catch(UnsupportedLookAndFeelException e){
            System.out.printf("Look and feel Nimbus not found !\n");
        }
        
        for (int i = 0; i < 4; i++) {
            decks.add(null);
        }
        
        loginUI();

    }
    
    public static void saveDecks(){
        ObjectOutputStream outputStream = null;

        try {
            File file = new File("src/resources/decks");

            if(!file.exists()){
                file.createNewFile();
            }
            
            final FileOutputStream fileStream = new FileOutputStream(file);
            outputStream = new ObjectOutputStream(fileStream);
            logFile.writeLog("Garage: Serializing decks ...");
            outputStream.writeObject(decks);

            outputStream.flush();
            
            
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (final IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void loadOverList(){
              
        ObjectInputStream inputStream = null;

        try {
            File file = new File("src/resources/overList");

            if(!file.exists()){
                file.createNewFile();
            }
            else{
                final FileInputStream fileStream = new FileInputStream(file);

                if(fileStream.available() > 0){
                                             
                    inputStream = new ObjectInputStream(fileStream);
                    logFile.writeLog("Garage: Deserializing over list ...");

                    overList = (ArrayList<Work>) inputStream.readObject();
                }
            }
            
        } catch (final java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void saveOverList(){
        ObjectOutputStream outputStream = null;

        try {
            File file = new File("src/resources/overList");

            if(!file.exists()){
                file.createNewFile();
            }
            
            final FileOutputStream fileStream = new FileOutputStream(file);
            outputStream = new ObjectOutputStream(fileStream);
            logFile.writeLog("Garage: Serializing over list ...");

            outputStream.writeObject(overList);

            outputStream.flush();
            
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (final IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void loadDecks(){
              
        ObjectInputStream inputStream = null;

        try {
            File file = new File("src/resources/decks");

            if(!file.exists()){
                file.createNewFile();
            }
            else{
                final FileInputStream fileStream = new FileInputStream(file);

                if(fileStream.available() > 0){
                                             
                    inputStream = new ObjectInputStream(fileStream);
                    
                    logFile.writeLog("Garage: Deserializing decks ...");

                    decks = (ArrayList<Work>) inputStream.readObject();
                    
                    if(decks.get(0) != null)
                        workShopFrame.changeGround(decks.get(0).toString());
                    if(decks.get(1) != null)
                        workShopFrame.changeDeck1(decks.get(1).toString());
                    if(decks.get(2) != null)
                        workShopFrame.changeDeck2(decks.get(2).toString());
                    if(decks.get(3) != null)
                        workShopFrame.changeDeck3(decks.get(3).toString());

                }
            }
            
        } catch (final java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void loadWaitingWorkList(){
        
        ObjectInputStream inputStream = null;

        try {
            File file = new File("src/resources/waitingWork");

            if(!file.exists()){
                file.createNewFile();
            }
            else{
                final FileInputStream fileStream = new FileInputStream(file);

                if(fileStream.available() > 0){
                                             
                    inputStream = new ObjectInputStream(fileStream);
                    logFile.writeLog("Garage: Deserializing work list ...");

                    waitingWorkList = (ArrayList<Work>) inputStream.readObject();   
                    
                }
            }
            
        } catch (final java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
   
    public static void saveWaitingWorkList(){
        ObjectOutputStream outputStream = null;

        try {
            File file = new File("src/resources/waitingWork");

            if(!file.exists()){
                file.createNewFile();
            }
            
            final FileOutputStream fileStream = new FileOutputStream(file);
            outputStream = new ObjectOutputStream(fileStream);
            outputStream.writeObject(waitingWorkList);
            logFile.writeLog("Garage: Serializing work list ...");

            outputStream.flush();
            
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (final IOException ex) {
                    ex.printStackTrace();
            }
        }
    }    
    
    public static void orderUIParts(){
        
        if(clientParts == null){
            clientParts = new NetworkBasicClient("localhost", 11000);

        }
        
        orderFrameParts = new OrderFrame(0);
        Dimension size = new Dimension(1200,800);
        orderFrameParts.setSize(size);
        orderFrameParts.setBackground(Color.GRAY);
        orderFrameParts.setMinimumSize(size);
        orderFrameParts.setTitle("Garage HEPL - Order Parts");
        orderFrameParts.setLocationRelativeTo(null);
        orderFrameParts.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrameParts.setVisible(true);
    }
    
    public static void closeOrderUIParts(Order order){
        if(orderFrameParts.isVisible()){
                        
            if(order != null){

                String responseString = clientParts.sendString(order.toSend());

                String [] rowSplit = responseString.split(";");

                OrderPriorityEnum priority = OrderPriorityEnum.NOT_PRIORITIZE;

                for(OrderPriorityEnum priorityEnum : OrderPriorityEnum.values()){
                    if(priorityEnum.getValue() == Integer.parseInt(rowSplit[3])){
                        priority = priorityEnum;
                    }
                }

                Order orderResponse = new Order(rowSplit[0], rowSplit[1], Integer.parseInt(rowSplit[2]), priority);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
                
                LocalDateTime expeditionDate = LocalDateTime.parse(rowSplit[5], dtf);

                Response response = new Response(orderResponse, rowSplit[4].equals("1"), expeditionDate);
                orderDoneListParts.add(response);
                
                rowSplit[3] = rowSplit[4].equals("1") ? "Available" : "Not available";
                rowSplit[4] = rowSplit[5];
                
                orderFrameParts.model.addRow(rowSplit);

                if(response.isAvailable()){
                    JOptionPane.showMessageDialog(orderFrameParts,"Commande : " +  orderResponse.toString() + " acceptée et prête à l'éxpédition !", "Commande acceptée", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                    JOptionPane.showMessageDialog(orderFrameParts,"Commande : " +  orderResponse.toString() + " refusée !", "Commande refusée", JOptionPane.WARNING_MESSAGE);            
                }
            }
            else{  
                orderFrameParts.setVisible(false);
                orderFrameParts.dispose();
            }
        }
    }
   
    public static void orderUITires(){
        
        if(clientTires == null){
            clientTires = new NetworkBasicClient("localhost", 11001);

        }
        orderFrameTires = new OrderFrame(1);
        Dimension size = new Dimension(1200,800);
        orderFrameTires.setSize(size);
        orderFrameTires.setBackground(Color.GRAY);
        orderFrameTires.setMinimumSize(size);
        orderFrameTires.setTitle("Garage HEPL - Order Tires");
        orderFrameTires.setLocationRelativeTo(null);
        orderFrameTires.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrameTires.setVisible(true);
    }
    
    public static void closeOrderUITires(Order order){
        if(orderFrameTires.isVisible()){
                        
            if(order != null){

                String responseString = clientTires.sendString(order.toSend());

                String [] rowSplit = responseString.split(";");

                OrderPriorityEnum priority = OrderPriorityEnum.NOT_PRIORITIZE;

                for(OrderPriorityEnum priorityEnum : OrderPriorityEnum.values()){
                    if(priorityEnum.getValue() == Integer.parseInt(rowSplit[3])){
                        priority = priorityEnum;
                    }
                }

                Order orderResponse = new Order(rowSplit[0], rowSplit[1], Integer.parseInt(rowSplit[2]), priority);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
                
                LocalDateTime expeditionDate = LocalDateTime.parse(rowSplit[5], dtf);

                Response response = new Response(orderResponse, rowSplit[4].equals("1"), expeditionDate);
                orderDoneListTires.add(response);
                
                rowSplit[3] = rowSplit[4].equals("1") ? "Available" : "Not available";
                rowSplit[4] = rowSplit[5];
                
                orderFrameTires.model.addRow(rowSplit);

                if(response.isAvailable()){
                    JOptionPane.showMessageDialog(orderFrameTires,"Commande : " +  orderResponse.toString() + " acceptée et prête à l'éxpédition !", "Commande acceptée", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                    JOptionPane.showMessageDialog(orderFrameTires,"Commande : " +  orderResponse.toString() + " refusée !", "Commande refusée", JOptionPane.WARNING_MESSAGE);            
                }
            }
            else{  
                orderFrameTires.setVisible(false);
                orderFrameTires.dispose();
            }
        }
    }    
    
    public static void orderUILubricant(){
        
        if(clientLubricant == null){
            clientLubricant = new NetworkBasicClient("localhost", 11002);
        }
        
        orderFrameLubricant = new OrderFrame(2);
        Dimension size = new Dimension(1200,800);
        orderFrameLubricant.setSize(size);
        orderFrameLubricant.setBackground(Color.GRAY);
        orderFrameLubricant.setMinimumSize(size);
        orderFrameLubricant.setTitle("Garage HEPL - Order Lubricants");
        orderFrameLubricant.setLocationRelativeTo(null);
        orderFrameLubricant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrameLubricant.setVisible(true);
    }
    
    public static void closeOrderUILubricant(Order order){
        if(orderFrameLubricant.isVisible()){
                        
            if(order != null){

                String responseString = clientLubricant.sendString(order.toSend());

                String [] rowSplit = responseString.split(";");

                OrderPriorityEnum priority = OrderPriorityEnum.NOT_PRIORITIZE;

                for(OrderPriorityEnum priorityEnum : OrderPriorityEnum.values()){
                    if(priorityEnum.getValue() == Integer.parseInt(rowSplit[3])){
                        priority = priorityEnum;
                    }
                }

                Order orderResponse = new Order(rowSplit[0], rowSplit[1], Integer.parseInt(rowSplit[2]), priority);
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
                
                LocalDateTime expeditionDate = LocalDateTime.parse(rowSplit[5], dtf);
                
                Response response = new Response(orderResponse, rowSplit[4].equals("1"), expeditionDate);
                
                orderDoneListLubricant.add(response);
                
                rowSplit[3] = rowSplit[4].equals("1") ? "Available" : "Not available";
                rowSplit[4] = rowSplit[5];
                
                orderFrameLubricant.model.addRow(rowSplit);

                if(response.isAvailable()){
                    JOptionPane.showMessageDialog(orderFrameLubricant,"Commande : " +  orderResponse.toString() + " acceptée et prête à l'éxpédition !", "Commande acceptée", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                    JOptionPane.showMessageDialog(orderFrameLubricant,"Commande : " +  orderResponse.toString() + " refusée !", "Commande refusée", JOptionPane.WARNING_MESSAGE);            
                }
            }
            else{  
                orderFrameLubricant.setVisible(false);
                orderFrameLubricant.dispose();
            }
        }
    }
    
    public static void loginUI(){
        
        loginFrame = new LoginFrame();
        Dimension size = new Dimension(400,250);
        loginFrame.setSize(size);
        loginFrame.setBackground(Color.GRAY);
        loginFrame.setMinimumSize(size);
        loginFrame.setTitle("Garage HEPL - Authentification");
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }
    
    public static void closeLoginUI(){
        if(loginFrame.isVisible()){
            loginFrame.setVisible(false);
            loginFrame.dispose();
        }
    }
    
    public static void workShopUI(){
               
        workShopFrame = new WorkShopFrame();
        clock = new Clock(workShopFrame.jLabelTime);
        clock.start();
        loadWaitingWorkList();
        loadDecks();
        loadOverList();
        Dimension size = new Dimension(1200,750);
        workShopFrame.setSize(size);
        workShopFrame.setBackground(Color.GRAY);
        workShopFrame.setMinimumSize(size);
        workShopFrame.setTitle("Garage HEPL - WorkShop");
        workShopFrame.setLocationRelativeTo(null);
        workShopFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        workShopFrame.setVisible(true);
    }
    
    public static void planificationUI(){
        toPlanFrame = new PlanificationFrame();
        Dimension size = new Dimension(800,600);
        toPlanFrame.setSize(size);
        toPlanFrame.setBackground(Color.GRAY);
        toPlanFrame.setMinimumSize(size);
        toPlanFrame.setTitle("Garage HEPL - WorkShop Planification");
        toPlanFrame.setLocationRelativeTo(null);
        toPlanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        toPlanFrame.setVisible(true);
    }
      
    public static void closePlanificationUI(Work work){
        if(toPlanFrame.isVisible()){
            if(work != null){
                waitingWorkList.add(work);
                saveWaitingWorkList();
            }
            
            toPlanFrame.setVisible(false);
            toPlanFrame.dispose();
        }
    }
    
    public static void startJobUI(){
        startFrame = new StartFrame();
        Dimension size = new Dimension(700,800);
        startFrame.setSize(size);
        startFrame.setBackground(Color.GRAY);
        startFrame.setMinimumSize(size);
        startFrame.setTitle("Garage HEPL - WorkShop Start a work");
        startFrame.setLocationRelativeTo(null);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setVisible(true);
    }
    
    public static void closeStartJobUI(){
        if(startFrame.isVisible()){
            startFrame.setVisible(false);
            startFrame.dispose();
        }
    }
    
    public static void overListUI(){
        overListFrame = new OverListFrame();
        Dimension size = new Dimension(700,800);
        overListFrame.setSize(size);
        overListFrame.setBackground(Color.GRAY);
        overListFrame.setMinimumSize(size);
        overListFrame.setTitle("Garage HEPL - WorkShop Over List");
        overListFrame.setLocationRelativeTo(null);
        overListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        overListFrame.setVisible(true);
    }
    
    public static void closeOverListUI(){
        if(overListFrame.isVisible()){
            overListFrame.setVisible(false);
            overListFrame.dispose();
        }
    }
    
    public static void aboutUI(){
        
        aboutFrame = new AboutFrame();
        Dimension size = new Dimension(300,300);
        aboutFrame.setSize(size);
        aboutFrame.setBackground(Color.GRAY);
        aboutFrame.setMinimumSize(size);
        aboutFrame.setTitle("Garage HEPL - WorkShop About");
        aboutFrame.setLocationRelativeTo(null);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutFrame.setVisible(true);
    }
    
    public static void closeAboutUI(){
        if(aboutFrame.isVisible()){
            aboutFrame.setVisible(false);
            aboutFrame.dispose();
        }
    }
    
    public static void systemInfoUI(){
        
        systemInfoFrame = new SystemInfoFrame();
        Dimension size = new Dimension(600,600);
        systemInfoFrame.setSize(size);
        systemInfoFrame.setBackground(Color.GRAY);
        systemInfoFrame.setMinimumSize(size);
        systemInfoFrame.setTitle("Garage HEPL - WorkShop System Info");
        systemInfoFrame.setLocationRelativeTo(null);
        systemInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        systemInfoFrame.setVisible(true);
    }
    
    public static void closeSystemInfoUI(){
        if(systemInfoFrame.isVisible()){
            systemInfoFrame.setVisible(false);
            systemInfoFrame.dispose();
        }
    }
    
    public static ArrayList<Work> getWaitingWorkList(){
        
        return waitingWorkList;
        
    }
    
    public static ArrayList<Work> getOverList(){
        
        return overList;
        
    }
    
    public static Work getDeck(int index){
        return decks.get(index);
    }
    
    public static boolean setDeck(int index, Work deck){
        if(decks.get(index) == null){
            decks.set(index, deck);
            if(deck != null){
                switch(index){
                    case 0 -> workShopFrame.changeGround(deck.toString());
                    case 1 -> workShopFrame.changeDeck1(deck.toString());
                    case 2 -> workShopFrame.changeDeck2(deck.toString());
                    case 3 -> workShopFrame.changeDeck3(deck.toString());
                }
            }
            return true;
        }
        return false;
    }
    
    public static void endDeck(int index){
        if(decks.get(index) != null){

            switch(index){
                case 0 -> workShopFrame.changeGround("Available");
                case 1 -> workShopFrame.changeDeck1("Available");
                case 2 -> workShopFrame.changeDeck2("Available");
                case 3 -> workShopFrame.changeDeck3("Available");
            }
            overList.add(decks.get(index));
            decks.set(index, null);
            Main.saveDecks();
            Main.saveOverList();
            
        }
        
    }

    public static void setCurrentUser(GarageStaff user){
        Main.currentUser = user;
    }
    
    public static GarageStaff getCurrentUser(){
        
        return currentUser;
    }
    
    public static ArrayList<GarageStaff> getUsers(){
        return users;
    }
    
    public static void removeWorkFromWaitingList(Work work){
        waitingWorkList.remove(work);
        Main.saveWaitingWorkList();

    }
    
}
