package org.oldjopa.tpo1.task3;

import java.util.ArrayList;
import java.util.List;

public class ObservationRegistry {
    private List<Observation> observations;

    public ObservationRegistry() {
        this.observations = new ArrayList<>();
    }

    public void addObservation(Observation observation) {
        this.observations.add(observation);
    }

    public List<Observation> getObservations() {
        return observations;
    }
}
