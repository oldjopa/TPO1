package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oldjopa.tpo1.task3.*;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    private Planet earth;
    private SpaceObject moon;
    private SpaceObject satellite;
    private Cosmodrome cosmodrome;
    private Observatory observatory;

    @BeforeEach
    void setUp() {
        earth = new Planet("Earth");
        moon = new SpaceObject("Moon", true);
        satellite = new SpaceObject("Satellite", false);
        cosmodrome = new Cosmodrome("Gonhillie", "UK");
        observatory = new Observatory("Wumera", "Australia");

        earth.addSpaceObject(moon);
        earth.addSpaceObject(satellite);
        earth.addCosmodrome(cosmodrome);
        earth.addObservatory(observatory);
    }

    @Test
    void testAddSpaceObject() {
        assertEquals(2, earth.getSpaceObjects().size());
    }

    @Test
    void testSpaceObjectVisibility() {
        assertTrue(moon.isVisible());
        assertFalse(satellite.isVisible());

        satellite.setVisible(true);
        assertTrue(satellite.isVisible());
    }

    @Test
    void testNotifyObservers() {
        Observation observation = ObservationFactory.createObservation("2025-02-19", "Moon was visible, Satellite was not visible.");

        earth.notifyObservers(observation);

        assertEquals(1, cosmodrome.getObservationRegistry().getObservations().size());
        assertEquals("Moon was visible, Satellite was not visible.", cosmodrome.getObservationRegistry().getObservations().get(0).getDetails());

        assertEquals(1, observatory.getObservationRegistry().getObservations().size());
        assertEquals("Moon was visible, Satellite was not visible.", observatory.getObservationRegistry().getObservations().get(0).getDetails());
    }

    @Test
    void testObservationRegistry() {
        Observation observation = ObservationFactory.createObservation("2025-02-19", "Moon was visible.");
        earth.notifyObservers(observation);

        assertEquals(1, cosmodrome.getObservationRegistry().getObservations().size());
        assertEquals("Moon was visible.", cosmodrome.getObservationRegistry().getObservations().get(0).getDetails());

        assertEquals(1, observatory.getObservationRegistry().getObservations().size());
        assertEquals("Moon was visible.", observatory.getObservationRegistry().getObservations().get(0).getDetails());
    }

    @Test
    void testNotificationWithMultipleObservers() {
        Cosmodrome secondCosmodrome = new Cosmodrome("Baikonur", "Kazakhstan");
        Observatory secondObservatory = new Observatory("Mauna Kea", "Hawaii");

        earth.addCosmodrome(secondCosmodrome);
        earth.addObservatory(secondObservatory);

        Observation observation = ObservationFactory.createObservation("2025-02-19", "Satellite is visible.");
        earth.notifyObservers(observation);

        assertEquals(1, cosmodrome.getObservationRegistry().getObservations().size());
        assertEquals("Satellite is visible.", cosmodrome.getObservationRegistry().getObservations().get(0).getDetails());

        assertEquals(1, secondCosmodrome.getObservationRegistry().getObservations().size());
        assertEquals("Satellite is visible.", secondCosmodrome.getObservationRegistry().getObservations().get(0).getDetails());

        assertEquals(1, observatory.getObservationRegistry().getObservations().size());
        assertEquals("Satellite is visible.", observatory.getObservationRegistry().getObservations().get(0).getDetails());

        assertEquals(1, secondObservatory.getObservationRegistry().getObservations().size());
        assertEquals("Satellite is visible.", secondObservatory.getObservationRegistry().getObservations().get(0).getDetails());
    }
}
