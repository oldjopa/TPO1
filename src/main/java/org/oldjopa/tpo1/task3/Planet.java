package org.oldjopa.tpo1.task3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    @Getter
    private List<SpaceObject> spaceObjects;
    private List<Cosmodrome> cosmodromes;
    private List<Observatory> observatories;

    public Planet(String name) {
        this.name = name;
        this.spaceObjects = new ArrayList<>();
        this.cosmodromes = new ArrayList<>();
        this.observatories = new ArrayList<>();
    }

    public void addSpaceObject(SpaceObject spaceObject) {
        this.spaceObjects.add(spaceObject);
    }

    public void addCosmodrome(Cosmodrome cosmodrome) {
        this.cosmodromes.add(cosmodrome);
    }

    public void addObservatory(Observatory observatory) {
        this.observatories.add(observatory);
    }

    public void notifyObservers(Observation observation) {
        for (Cosmodrome cosmodrome : cosmodromes) {
            cosmodrome.notify(observation);
        }
        for (Observatory observatory : observatories) {
            observatory.notify(observation);
        }
    }
}
