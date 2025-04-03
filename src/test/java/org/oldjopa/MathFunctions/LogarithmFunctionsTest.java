package org.oldjopa.MathFunctions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.oldjopa.math.BigFunction;
import org.oldjopa.math.logarithm.Ln;
import org.oldjopa.math.logarithm.Log;
import org.oldjopa.math.trigonometry.*;
import org.oldjopa.utils.CalculatedPointLogarithmDTO;
import org.oldjopa.utils.CsvReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class LogarithmFunctionsTest {
    private static final List<CalculatedPointLogarithmDTO> valueList = new ArrayList<>();
    private static final double accuracy = 0.0000001;
    private static final int roundDigits = 5;

    private static final Map<Double, Double>  normalLogValues = new HashMap<>();

    @BeforeAll
    static void buildValueList() {
        CsvReader.readDataLogarithm("test_values_logarithm.csv", valueList);
        normalLogValues.put(10.0, 2.302585092994046);
        normalLogValues.put(2.0, 0.6931471805599453);
        normalLogValues.put(3.0, 1.0986122886681098);
        normalLogValues.put(5.0, 1.6094379124341003);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    static Stream<Arguments> provideArguments() {
        return valueList.stream().map(Arguments::of);
    }


    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction1lvl(CalculatedPointLogarithmDTO pointDTO) {
        Cos cos = new Cos();

        Sin sin = new Sin(cos);
        Sec sec = new Sec(cos);

        Csc csc = new Csc(sin);
        Tan tan = new Tan(sin, cos);
        Cot cot = new Cot(sin, cos);

        Ln ln = Mockito.mock(Ln.class);
        Log log10 = new Log(10, ln);
        Log log2 = new Log(2, ln);
        Log log5 = new Log(5, ln);
        Log log3 = new Log(3, ln);

        when(ln.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getLnValue());
        when(ln.compute(10, accuracy)).thenReturn(normalLogValues.get(10.0));
        when(ln.compute(2, accuracy)).thenReturn(normalLogValues.get(2.0));
        when(ln.compute(3, accuracy)).thenReturn(normalLogValues.get(3.0));
        when(ln.compute(5, accuracy)).thenReturn(normalLogValues.get(5.0));


        BigFunction system = new BigFunction(sec, tan, csc, sin, cos, cot, log3, log5, log10, log2);

        double result = system.compute(pointDTO.getArgument(), accuracy);
        assertEquals(pointDTO.getTargetValue(), round(result, roundDigits));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction2lvl(CalculatedPointLogarithmDTO pointDTO) {
        Cos cos = new Cos();

        Sin sin = new Sin(cos);
        Sec sec = new Sec(cos);

        Csc csc = new Csc(sin);
        Tan tan = new Tan(sin, cos);
        Cot cot = new Cot(sin, cos);

        Ln ln = new Ln();
        Log log10 = new Log(10, ln);
        Log log2 = new Log(2, ln);
        Log log5 = new Log(5, ln);
        Log log3 = new Log(3, ln);



        BigFunction system = new BigFunction(sec, tan, csc, sin, cos, cot, log3, log5, log10, log2);

        double result = system.compute(pointDTO.getArgument(), accuracy);
        assertTrue(isAccurate(pointDTO.getTargetValue(), round(result, roundDigits)));
    }

    private static boolean isAccurate(double target, double actual) {
        return target == actual || Math.abs(Math.abs(target - actual) / target) < (double) roundDigits / 100;
    }

    private static double round(double value, int places) {
        return BigDecimal.valueOf(value)
                .setScale(places, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
