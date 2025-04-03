package org.oldjopa.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Tan implements MathFunction {
    private final MathFunction sin;
    private final MathFunction cos;

    @Override
    public double compute(double x, double accuracy) {
        double cosValue = cos.compute(x, accuracy);
//        if (cosValue < accuracy) {
//            throw new ArithmeticException("Divided by zero");
//        }
        return sin.compute(x, accuracy) / cosValue;
    }

}
