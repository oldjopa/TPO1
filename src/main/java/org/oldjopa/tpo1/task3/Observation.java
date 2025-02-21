package org.oldjopa.tpo1.task3;

public class Observation {
    private String date;
    private String details;

    public Observation(String date, String details) {
        this.date = date;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

//    public String toString() {
//        return "Observation on " + date + ": " + details;
//    }
}
