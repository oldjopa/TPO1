package org.oldjopa.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Sec  implements MathFunction {
    private final MathFunction cos;

    @Override
    public double compute(double x, double accuracy) {
        return 1 / cos.compute(x, accuracy);
    }

}
