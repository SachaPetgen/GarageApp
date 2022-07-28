/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.gerdaypetgen.activity;

/**
 *
 * @author sacha
 */
public class Order {
    
    
    
    private String name;
    private String type;
    private int quantity;
    private OrderPriorityEnum priority;

    public Order(String name, String type, int quantity, OrderPriorityEnum priority) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderPriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(OrderPriorityEnum priority) {
        this.priority = priority;
    }
    
    
    public String toSend(){
        
        return name + ";" + type + ";" + quantity + ";" + priority.getValue();
        
    }
    
    @Override
    public String toString(){
        
        return name + " " + type + " " + quantity + " " + priority.name();

    }
    
}
