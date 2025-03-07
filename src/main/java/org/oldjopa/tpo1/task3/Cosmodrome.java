package org.oldjopa.tpo1.task3;

import lombok.Getter;

public class Cosmodrome implements Observer {
    private final ObservationFactory observationFactory;
    @Getter
    private String name;
    private boolean is_sad = false;
    private String location;
    @Getter
    private ObservationRegistry observationRegistry;

    public Cosmodrome(String name, String location) {
        this.name = name;
        this.location = location;
        this.observationRegistry = new ObservationRegistry();
        this.observationFactory = new ObservationFactory();

    }

    @Override
    public void notify(SpaceObject spaceObject) {
        if (spaceObject.isVisible() || this.is_sad) {
            this.observationRegistry.addObservation(observationFactory.createObservation(spaceObject, this));
        } else {
            this.is_sad = true;
        }
    }

}


