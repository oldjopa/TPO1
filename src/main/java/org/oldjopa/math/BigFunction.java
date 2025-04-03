package org.oldjopa.math;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.logarithm.Log;
import org.oldjopa.math.trigonometry.*;

@RequiredArgsConstructor
public class BigFunction implements MathFunction {
    private final MathFunction sec;
    private final MathFunction tan;
    private final MathFunction csc;
    private final MathFunction sin;
    private final MathFunction cos;
    private final MathFunction cot;

    private final MathFunction log3;
    private final MathFunction log5;
    private final MathFunction log10;
    private final MathFunction log2;

    @Override
    public double compute(double x, double accuracy) {
        if (x <= 0) {
            double secX = sec.compute(x, accuracy);
            double tanX = tan.compute(x, accuracy);
            double cscX = csc.compute(x, accuracy);
            double sinX = sin.compute(x, accuracy);
            double cosX = cos.compute(x, accuracy);
            double cotX = cot.compute(x, accuracy);

            double part1 = Math.pow((((secX - tanX) * cscX) - cscX - sinX), 3) + (secX / tanX);
            double part2 = Math.pow(part1, 3);

            double part3 = ((secX / cosX) * Math.pow((cosX + (cscX / sinX)) / cotX, 3)) * cosX;
            double part4 = part3 / ((cotX + cosX) * secX);

            return part2 / part4;
        } else {
            double log3X = log3.compute(x, accuracy);
            double log5X = log5.compute(x, accuracy);
            double log10X = log10.compute(x, accuracy);
            double log2X = log2.compute(x, accuracy);

            double part1 = Math.pow(((log3X / log5X) * log10X) / log2X, 3);
            double part2 = Math.pow(log10X, 3);

            return part1 + part2;
        }
    }
}
