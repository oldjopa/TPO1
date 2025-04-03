package org.oldjopa.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Cot implements MathFunction {
    private final MathFunction sin;
    private final MathFunction cos;

    @Override
    public double compute(double x, double accuracy) {
        return cos.compute(x, accuracy) / sin.compute(x, accuracy);
    }

}
