package be.gerdaypetgen.people;

import java.io.Serializable;

public class Person implements Serializable{
    
    protected String name;
    protected String firstName;
    protected String address;
    protected String telNumber;
    
    public Person(String n, String fN, String add, String tN){
        name = n;
        firstName = fN;
        address = add;
        telNumber = tN;
    }
    
    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
