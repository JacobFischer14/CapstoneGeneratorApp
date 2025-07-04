package com.example.generatorapp;

public class Generator {
    private int id;
    private String modelNumber;
    private String currentA;
    private String currentB;
    private String currentC;
    private float frequency;

    public Generator(int id, String modelNumber, String currentA, String currentB, String currentC, float frequency)
    {
        this.id = id;
        this.modelNumber = modelNumber;
        this.currentA = currentA;
        this.currentB = currentB;
        this.currentC = currentC;
        this.frequency = frequency;
    }

    public float getFrequency()
    {
        return frequency;
    }

    public int getId() { return id; }
    public String getModelNumber() { return modelNumber; }
    public String getCurrentA() { return currentA; }
    public String getCurrentB() { return currentB; }
    public String getCurrentC() { return currentC; }

    public void setModelNumber(String modelNumber) { this.modelNumber = modelNumber; }
    public void setCurrentA(String currentA) { this.currentA = currentA; }
    public void setCurrentB(String currentB) { this.currentB = currentB; }
    public void setCurrentC(String currentC) { this.currentC = currentC; }
}

