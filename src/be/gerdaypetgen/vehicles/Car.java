package be.gerdaypetgen.vehicles;

import be.gerdaypetgen.people.Client;

public class Car extends Vehicle{
    
    
    private CarType type;

    public Car(CarType type, Client client) {
        super(client);
        this.type = type;

    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }
    
    
    @Override
    public String toString(){
        return type.toString() +  " " + client.toString();
    }
    
}
