package com.example.jakartatest.Entities;

public class Ogretmen {
    private long id;
    private String name;
    private boolean isGıcık;

    public Ogretmen() {
    }

    public Ogretmen(String name, boolean isGıcık) {
        this.name = name;
        this.isGıcık = isGıcık;
    }

    public Ogretmen(long id, String name, boolean isGıcık) {
        this.id = id;
        this.name = name;
        this.isGıcık = isGıcık;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGıcık() {
        return isGıcık;
    }

    public void setGıcık(boolean gıcık) {
        isGıcık = gıcık;
    }

    @Override
    public String toString() {
        return "Ogretmen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isGıcık=" + isGıcık +
                "}\n";
    }
}
