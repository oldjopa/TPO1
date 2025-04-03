package org.oldjopa.math.logarithm;

import lombok.RequiredArgsConstructor;
import org.oldjopa.math.MathFunction;

@RequiredArgsConstructor
public class Log implements MathFunction {

    private final int base;
    private final MathFunction ln;

    @Override
    public double compute(double x, double accuracy) {
        return ln.compute(x, accuracy) / ln.compute(base, accuracy);
    }

}