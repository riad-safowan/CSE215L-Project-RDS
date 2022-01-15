package com.nsu.rds.data.models;

import java.io.Serializable;

public class Courses implements Serializable {
    private String initial;
    private String name;
    private double credit;

    public Courses(String initial, String name, double credit) {
        this.initial = initial;
        this.name = name;
        this.credit = credit;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
