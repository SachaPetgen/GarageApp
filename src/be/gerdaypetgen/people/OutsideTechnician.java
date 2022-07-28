package be.gerdaypetgen.people;

import be.gerdaypetgen.authenticate.Identifiable;

public class OutsideTechnician extends Person implements Identifiable {
    
    private String outsideTechId;
    
    public OutsideTechnician(String n, String fN, String add, String tN, String outsideTechId){
        
        super(n, fN, add, tN);
        this.outsideTechId = outsideTechId;
        
    }

    @Override
    public String getId() {
        return this.outsideTechId;
    }

    @Override
    public void setId(String i) {
        this.outsideTechId = i;
    }

}
