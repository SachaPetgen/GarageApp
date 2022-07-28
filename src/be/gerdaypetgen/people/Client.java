package be.gerdaypetgen.people;

public class Client extends Person{
    
    
    private static int clientIDGiver;
    private String clientID;
    
    
    public Client(String n, String fN, String add, String tN){
        
        super(n, fN, add, tN);
        this.clientID = String.valueOf(clientIDGiver);
        clientIDGiver++;        
    }
    
    public Client(String n){
        
        super(n, "", "", "");
        this.clientID = String.valueOf(clientIDGiver);
        clientIDGiver++;
        
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
