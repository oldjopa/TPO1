package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oldjopa.tpo1.task3.*;

import java.security.cert.TrustAnchor;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    private Planet earth;
    private SpaceObject moon;
    private SpaceObject satellite;
    private Cosmodrome cosmodrome;
    private Observatory observatory;

    private ObservationFactory observationFactory;

    @BeforeEach
    void setUp() {
        earth = new Planet("Earth");
        moon = new SpaceObject("Moon", true);
        satellite = new SpaceObject("Satellite", false);
        cosmodrome = new Cosmodrome("Gonhillie", "UK");
        observatory = new Observatory("Wumera", "Australia");
        observationFactory = new ObservationFactory();

        earth.addCosmodrome(cosmodrome);
        earth.addObservatory(observatory);

        earth.addSpaceObject(moon);
        earth.addSpaceObject(satellite);

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
        assertEquals(1, cosmodrome.getObservationRegistry().getObservations().size());

        assertEquals(1, observatory.getObservationRegistry().getObservations().size());
    }

    @Test
    void testObservationRegistry() {
        assertEquals(1, cosmodrome.getObservationRegistry().getObservations().size());

        assertEquals(1, observatory.getObservationRegistry().getObservations().size());
    }

//    @Test
//    void testNotificationWithMultipleObservers() {
//        Cosmodrome secondCosmodrome = new Cosmodrome("Baikonur", "Kazakhstan");
//        Observatory secondObservatory = new Observatory("Mauna Kea", "Hawaii");
//
//        earth.addCosmodrome(secondCosmodrome);
//        earth.addObservatory(secondObservatory);
//
//        assertEquals(0, secondCosmodrome.getObservationRegistry().getObservations().size());
//
//        assertEquals(0, secondObservatory.getObservationRegistry().getObservations().size());
//    }

    @Test
    void testCreateCorrectDescription() {
        SpaceObject asteroid = new SpaceObject("big ass asteroid", true);
        earth.addSpaceObject(asteroid);

        Observation observation = observationFactory.createObservation(asteroid, observatory);

        assertTrue(observatory.getObservationRegistry().getObservations().stream().anyMatch((o) -> o.getDetails().equals(observation.getDetails())));
    }

    @Test
    void testSkipInvisibleObjects(){
        SpaceObject asteroid = new SpaceObject("small ass asteroid", false);
        Cosmodrome secondCosmodrome = new Cosmodrome("Baikonur", "Kazakhstan");
        Observatory secondObservatory = new Observatory("Mauna Kea", "Hawaii");

        earth.addCosmodrome(secondCosmodrome);
        earth.addObservatory(secondObservatory);
        earth.addSpaceObject(asteroid);

        assertEquals(0, secondCosmodrome.getObservationRegistry().getObservations().size());
        assertEquals(0, secondObservatory.getObservationRegistry().getObservations().size());
    }

    @Test
    void testSadObserverHasUltravision(){
        SpaceObject asteroid = new SpaceObject("small ass asteroid", false);
        Cosmodrome secondCosmodrome = new Cosmodrome("Baikonur", "Kazakhstan");
        Observatory secondObservatory = new Observatory("Mauna Kea", "Hawaii");

        earth.addCosmodrome(secondCosmodrome);
        earth.addObservatory(secondObservatory);
        earth.addSpaceObject(asteroid);
        earth.addSpaceObject(asteroid);

        assertEquals(1, secondCosmodrome.getObservationRegistry().getObservations().size());
        assertEquals(1, secondObservatory.getObservationRegistry().getObservations().size());
    }
}
