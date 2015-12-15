package com.example.johannes.colorpicker;

/**
 * Created by a1402972 on 3.11.2015.
 */
public class Task {

    private int id;
    private String otsikko;
    private String aika;

    public Task() {
    }

    public Task(String headline, String aika) {
        this.otsikko = headline;
        this.aika = aika;
    }

    public Task(int id, String headline, String aika) {
        this.id = id;
        this.otsikko = headline;
        this.aika = aika;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getAika() {
        return aika;
    }
    public void setAika(String aika) {
        this.aika = aika;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", otsikko='" + otsikko + '\'' +
                ", aika='" + aika + '\'' +
                '}';
    }
}
