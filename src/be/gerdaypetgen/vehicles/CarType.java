package be.gerdaypetgen.vehicles;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sacha
 */
public class CarType implements Serializable {
    
    
    private String brand;
    private CarTypeEnum type;
    private int doorNumber;

    public CarType(String brand, CarTypeEnum type, int doorNumber) {
        this.brand = brand;
        this.type = type;
        this.doorNumber = doorNumber;
    }
    
    public CarType(String brand) {
        this.brand = brand;
        this.type = CarTypeEnum.DEFAULT;
        this.doorNumber = 4;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CarTypeEnum getType() {
        return type;
    }

    public void setType(CarTypeEnum type) {
        this.type = type;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        this.doorNumber = doorNumber;
    }
    
    
    @Override
    public String toString(){
        return brand;
    }
    
    
}
