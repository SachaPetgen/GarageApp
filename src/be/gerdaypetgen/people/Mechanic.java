package be.gerdaypetgen.people;

import java.io.Serializable;

public class Mechanic extends GarageStaff{
    
    private MechSpecEnum specialization;

    public Mechanic(String n, String fN, String add, String tN,String id, MechSpecEnum specialization) {
        super(n, fN, add, tN, id);
        this.specialization = specialization;
        
    }

    public MechSpecEnum getSpecialization() {
        return specialization;
    }

    public void setSpecialization(MechSpecEnum specialization) {
        this.specialization = specialization;
    }
}
