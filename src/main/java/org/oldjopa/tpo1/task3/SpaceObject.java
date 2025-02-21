package org.oldjopa.tpo1.task3;

import lombok.Getter;

public class SpaceObject {
    private String name;

    @Getter
    private boolean visible;

    public SpaceObject(String name, boolean visible) {
        this.name = name;
        this.visible = visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
