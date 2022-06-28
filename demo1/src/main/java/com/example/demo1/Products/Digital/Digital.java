package com.example.demo1.Products.Digital;

import com.example.demo1.Products.Category;
import com.example.demo1.Products.Product.Product;

public abstract class Digital extends Product {

    public static Category digitalCategory = new Category("Digital");

    private int memoryCapacity;
    private int RAM;
    private String OS;
    private double weight;
    private int size;

    public Digital(String name, String brand, double price, String description,
                   int memoryCapacity, int RAM, String OS, double weight, int size)
    {
        super(name, brand, price, description);
        this.memoryCapacity = memoryCapacity;
        this.RAM = RAM;
        this.OS = OS;
        this.weight = weight;
        this.size = size;
    }

    // Getters and Setters ================================================

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}