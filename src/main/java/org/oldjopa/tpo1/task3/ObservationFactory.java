package org.oldjopa.tpo1.task3;

public class ObservationFactory {
    public Observation createObservation(SpaceObject spaceObject, Observer observer) {
        String details = spaceObject.getName() + " is observed by " + observer.getName();
        return new Observation(details);
    }
}