package org.oldjopa.math.trigonometry;

import org.oldjopa.math.MathFunction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Cos implements MathFunction {
    @Override
    public double compute(double x, double accuracy) {
        x = x % (2 * Math.PI);
        double term = 1.0;
        double sum = term;
        int n = 1;
        MathContext mc = new MathContext(-1 * (int) Math.ceil(Math.log10(accuracy)) + 2, RoundingMode.HALF_UP);

        while (Math.abs(term) > accuracy) {
            term = BigDecimal.valueOf(-1).pow(n)
                    .multiply(BigDecimal.valueOf(x).pow(2 * n, mc))
                    .divide(factorial(2 * n), mc).doubleValue();
            sum += term;
            n++;
        }

        return sum;
    }

    private static BigDecimal factorial(int num) {
        if (num == 0) {
            return BigDecimal.ONE;
        }
        BigDecimal result = BigDecimal.ONE;
        for (int i = 1; i <= num; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }
}
