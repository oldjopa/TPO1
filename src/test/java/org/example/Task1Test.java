package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.oldjopa.tpo1.task1.TgDecomposition.*;

import org.oldjopa.tpo1.task1.TgDecomposition;


/**
 * Unit test for simple App.
 */
class Task1Test {

    private boolean fitsAccuracy(double a, double b, double accuracy) {
        return Math.abs(a - b) <= accuracy;
    }

    @ParameterizedTest(name = "Checking angle {0} degrees with tolerance {1}")
    @MethodSource("testParameters")
    void testTanAccuracy(int angle, double tolerance) {
        double angleInRadians = Math.toRadians(angle);
        BigDecimal angleInBigDecimal = BigDecimal.valueOf(angleInRadians);

        boolean isAccurate = fitsAccuracy(Math.tan(angleInRadians), tan(angleInBigDecimal, 20), tolerance);

        assertTrue(isAccurate, "Failed for angle: " + angle + " degrees, tolerance: " + tolerance);
    }

    static Stream<Arguments> testParameters() {
        return IntStream.of(-80, -70, -60, 0, 60, 70, 80)
                .mapToObj(angle -> Arguments.of(angle, getTolerance(angle)));
    }

    private static double getTolerance(int angle) {
        if (Math.abs(angle) >= 70) {
            return 0.5;
        } else if (Math.abs(angle) >= 60) {
            return 0.1;
        } else {
            return 0.001;
        }
    }


    @Test
    void classCreateTest() {
        TgDecomposition s = new TgDecomposition();
    }

}
