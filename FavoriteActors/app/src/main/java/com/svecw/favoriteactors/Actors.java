package com.svecw.favoriteactors;

public class Actors {
    int image;
    String name;
    int yob;

    public Actors(int image, String name, int yob) {
        this.image = image;
        this.name = name;
        this.yob = yob;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }
}
