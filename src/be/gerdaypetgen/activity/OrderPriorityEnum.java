/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package be.gerdaypetgen.activity;

import java.text.Normalizer;

/**
 *
 * @author sacha
 */
public enum OrderPriorityEnum {
   
    URGENT(0),
    NORMAL(1),
    NOT_PRIORITIZE(2);
    
    private final int value;
   
    
    private OrderPriorityEnum(int value){
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }
    
}
