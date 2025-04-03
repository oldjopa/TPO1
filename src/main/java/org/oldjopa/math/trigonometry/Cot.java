package org.oldjopa.math.trigonometry;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Cot implements MathFunction {
    private final MathFunction sin;
    private final MathFunction cos;

    @Override
    public double compute(double x, double accuracy) {
        double sinValue = sin.compute(x, accuracy);
        if (Math.abs(sinValue) < accuracy){
            throw new ArithmeticException("Divided by zero");
        }
        return cos.compute(x, accuracy) / sinValue;
    }

}
