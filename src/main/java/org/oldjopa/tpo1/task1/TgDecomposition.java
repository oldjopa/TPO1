package org.oldjopa.tpo1.task1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TgDecomposition {

    private static BigDecimal bernoulli(int n) {
        BigDecimal result = BigDecimal.ZERO;
        MathContext mc = new MathContext(50);

        for (int k = 0; k <= n; k++) {
            BigDecimal jSum = BigDecimal.ZERO;
            BigDecimal bDecimal = BigDecimal.ONE;

            for (int j = 0; j <= k; j++) {
                BigDecimal jPowN = new BigDecimal(j).pow(n, mc);
                if (j % 2 == 0) {
                    jSum = jSum.add(bDecimal.multiply(jPowN, mc));
                } else {
                    jSum = jSum.subtract(bDecimal.multiply(jPowN, mc));
                }

                bDecimal = bDecimal.multiply(new BigDecimal(k - j), mc)
                        .divide(new BigDecimal(j + 1), mc);
            }

            result = result.add(jSum.divide(new BigDecimal(k + 1), mc));
        }
        return result;
    }


    public static double tan(BigDecimal x, int terms) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int n = 1; n <= terms; n++) {
            BigDecimal bernoulli = bernoulli(2 * n);
            BigDecimal numerator = new BigDecimal(Math.pow(-1, n + 1)).multiply(new BigDecimal(Math.pow(2, 2 * n)))
                    .multiply(new BigDecimal(Math.pow(2, 2 * n) - 1)).multiply(bernoulli);
            BigDecimal denominator = factorial(2 * n);
            BigDecimal term = numerator.divide(denominator, RoundingMode.HALF_UP).multiply(x.pow(2 * n - 1));
            sum = sum.add(term);
        }
        return sum.doubleValue();
    }

    private static BigDecimal factorial(int n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(new BigDecimal(i));
        }
        return result;
    }

}
