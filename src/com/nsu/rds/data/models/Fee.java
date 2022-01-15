package com.nsu.rds.data.models;

import java.io.Serializable;

public class Fee implements Serializable {
    private double creditFee;
    private double activityFee;
    private double computerLabFee;
    private double libraryFee;
    private double scienceLabFee;
    private double waiver;

    public Fee(double creditFee, double activityFee, double computerLabFee, double libraryFee, double scienceLabFee, double waiver) {
        this.creditFee = creditFee;
        this.activityFee = activityFee;
        this.computerLabFee = computerLabFee;
        this.libraryFee = libraryFee;
        this.scienceLabFee = scienceLabFee;
        this.waiver = waiver;
    }

    public Fee() {

    }

    public double getCreditFee() {
        return creditFee;
    }

    public void setCreditFee(double creditFee) {
        this.creditFee = creditFee;
    }

    public double getActivityFee() {
        return activityFee;
    }

    public void setActivityFee(double activityFee) {
        this.activityFee = activityFee;
    }

    public double getComputerLabFee() {
        return computerLabFee;
    }

    public void setComputerLabFee(double computerLabFee) {
        this.computerLabFee = computerLabFee;
    }

    public double getScienceLabFee() {
        return scienceLabFee;
    }

    public void setScienceLabFee(double scienceLabFee) {
        this.scienceLabFee = scienceLabFee;
    }

    public double getLibraryFee() {
        return libraryFee;
    }

    public void setLibraryFee(double libraryFee) {
        this.libraryFee = libraryFee;
    }

    public double getWaiver() {
        return waiver;
    }

    public void setWaiver(double waiver) {
        this.waiver = waiver;
    }
}
