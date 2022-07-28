package be.gerdaypetgen.people;

import be.gerdaypetgen.Main;
import be.gerdaypetgen.authenticate.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import javax.imageio.ImageIO;

public abstract class GarageStaff extends Person implements Identifiable, ToValidate, Serializable {
    

    private transient boolean connected;
    private String id;
    
    public GarageStaff(String n, String fN, String add, String tN, String id){
        super(n, fN, add, tN);
        this.connected = false;
        this.id = id;
    }
    
    @Override
    public boolean isValid() {
        return connected;
    }

    @Override
    public boolean validate(char[] password) {
        if(password != null && id != null){
            
            char [] goodPass = findPasswordById();
            
            if(goodPass != null && Arrays.equals(password, goodPass)){
                connected = true;
                return true;
            }
        }
        return false;
    }
    
    private char[] findPasswordById(){
        
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("resources/users.properties")) {
            
            Properties prop = new Properties();

            prop.load(in);       
            
            if(prop.getProperty(id) != null){
                return prop.getProperty(id).toCharArray();
            }
            
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String i) {
        this.id = i;
    }
    
    public static GarageStaff getMemberById(String id){
        for(GarageStaff user : Main.getUsers()){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
}
