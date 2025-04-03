package org.oldjopa.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Csc implements MathFunction {
    private final MathFunction sin;

    @Override
    public double compute(double x, double accuracy) {
        return 1 / sin.compute(x, accuracy);
    }
}
