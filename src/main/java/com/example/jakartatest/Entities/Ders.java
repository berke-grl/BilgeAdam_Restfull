package com.example.jakartatest.Entities;

public class Ders {
    private long id;
    private Ogretmen ogretmen;
    private Konu konu;

    public Ders() {
    }

    public Ders(long id, Ogretmen ogretmen, Konu konu) {
        this.id = id;
        this.ogretmen = ogretmen;
        this.konu = konu;
    }

    public Ders(Ogretmen ogretmen, Konu konu) {
        this.ogretmen = ogretmen;
        this.konu = konu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ogretmen getOgretmen() {
        return ogretmen;
    }

    public void setOgretmen(Ogretmen ogretmen) {
        this.ogretmen = ogretmen;
    }

    public Konu getKonu() {
        return konu;
    }

    public void setKonu(Konu konu) {
        this.konu = konu;
    }

    @Override
    public String toString() {
        return "Ders{" +
                "id=" + id +
                ", ogretmen=" + ogretmen +
                ", konu=" + konu +
                "}\n";
    }
}
