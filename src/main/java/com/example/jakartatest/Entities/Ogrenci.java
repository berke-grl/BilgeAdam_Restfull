package com.example.jakartatest.Entities;

public class Ogrenci {
    private long id;
    private String name;
    private long ogrNumber;
    private long year;

    public Ogrenci() {
    }

    public Ogrenci(String name, long ogrNumber, long year) {
        this.name = name;
        this.ogrNumber = ogrNumber;
        this.year = year;
    }

    public Ogrenci(long id, String name, long ogrNumber, long year) {
        this.id = id;
        this.name = name;
        this.ogrNumber = ogrNumber;
        this.year = year;
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

    public long getOgrNumber() {
        return ogrNumber;
    }

    public void setOgrNumber(long ogrNumber) {
        this.ogrNumber = ogrNumber;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Ogrenci{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ogrNumber=" + ogrNumber +
                ", year=" + year +
                "}\n";
    }
}
