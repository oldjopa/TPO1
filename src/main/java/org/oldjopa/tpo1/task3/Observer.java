package org.oldjopa.tpo1.task3;

public interface Observer {
    String getName();
    void notify(SpaceObject object);
}