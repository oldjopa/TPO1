package org.oldjopa.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Sec  implements MathFunction {
    private final MathFunction cos;

    @Override
    public double compute(double x, double accuracy) {
        double cosValue = cos.compute(x, accuracy);
        if (Math.abs(cosValue) < accuracy) {
            throw new ArithmeticException("Divided by zero");
        }
        return 1 / cosValue;
    }

}
