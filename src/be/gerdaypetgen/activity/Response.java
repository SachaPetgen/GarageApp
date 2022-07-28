/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.gerdaypetgen.activity;

import java.time.LocalDateTime;

/**
 *
 * @author sacha
 */
public class Response {
    
    
    private Order order;
    private boolean available;
    private LocalDateTime expeditionDate;

    public Response(Order order, boolean available, LocalDateTime expeditionDate) {
        this.order = order;
        this.available = available;
        this.expeditionDate = expeditionDate;
    }

    public Order getOrder() {
        return order;
    }
    
    public boolean isAvailable() {
        return available;
    }

    public LocalDateTime getExpeditionDate() {
        return expeditionDate;
    }
        
    public String toSend(){
        return order.toSend() + ";" + (available ? "1" : "0");
    }
    
    
}
