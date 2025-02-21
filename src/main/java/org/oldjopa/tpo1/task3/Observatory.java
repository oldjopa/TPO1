package org.oldjopa.tpo1.task3;

import lombok.Getter;

public class Observatory implements Observer {
    private String name;
    private String location;
    @Getter
    private ObservationRegistry observationRegistry;

    public Observatory(String name, String location) {
        this.name = name;
        this.location = location;
        this.observationRegistry = new ObservationRegistry();
    }

    @Override
    public void notify(Observation observation) {
        this.observationRegistry.addObservation(observation);
    }

}