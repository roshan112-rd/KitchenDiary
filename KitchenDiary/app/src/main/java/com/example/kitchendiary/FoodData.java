package com.example.kitchendiary;

public class FoodData {
    private String name;
    private String description ;
    private String time;
    private String image;

    public FoodData(String name, String description, String time, String image) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.image = image;
    }
    public FoodData(){

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }
}
