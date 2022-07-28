package be.gerdaypetgen.authenticate;

public interface ToValidate {
        
    boolean isValid();
    boolean validate(char[] password);
    
}
