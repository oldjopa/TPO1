package org.oldjopa.tpo1.task3;

public class ObservationFactory {
    public static Observation createObservation(String date, String details) {
        return new Observation(date, details);
    }
}