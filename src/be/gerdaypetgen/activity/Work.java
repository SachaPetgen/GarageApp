package be.gerdaypetgen.activity;

import be.gerdaypetgen.people.Mechanic;
import be.gerdaypetgen.vehicles.Vehicle;
import java.io.Serializable;

public abstract class Work implements Serializable{
    
    private Mechanic responsibleMech;
    private Vehicle vehicle;
    
    public Work(Mechanic responsibleMech, Vehicle vehicle) {
        this.responsibleMech = responsibleMech;
        this.vehicle = vehicle;
    }

    public Mechanic getResponsibleMech() {
        return responsibleMech;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString(){
        
        return vehicle.toString();
    }
}
