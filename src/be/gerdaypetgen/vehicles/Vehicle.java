package be.gerdaypetgen.vehicles;

import be.gerdaypetgen.people.Client;
import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    
    
    protected Client client;

    public Vehicle(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
}
