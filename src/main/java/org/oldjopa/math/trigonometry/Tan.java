package org.oldjopa.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Tan implements MathFunction {
    private final MathFunction sin;
    private final MathFunction cos;

    @Override
    public double compute(double x, double accuracy) {
        return sin.compute(x, accuracy) / cos.compute(x, accuracy);
    }

}
