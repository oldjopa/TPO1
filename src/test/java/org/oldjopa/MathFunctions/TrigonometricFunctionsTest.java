package org.oldjopa.MathFunctions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.oldjopa.math.BigFunction;
import org.oldjopa.math.trigonometry.*;
import org.oldjopa.math.logarithm.*;
import org.oldjopa.utils.CalculatedPointTrigonometryDTO;
import org.oldjopa.utils.CsvReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class TrigonometricFunctionsTest {

    private static final List<CalculatedPointTrigonometryDTO> valueList = new ArrayList<>();
    private static final double accuracy = 0.0000001;
    private static final int roundDigits = 5;

    @BeforeAll
    static void buildValueList() {
        CsvReader.readDataTrigonometry("test_values_trigonometry.csv", valueList);
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
    void testFunction1lvl(CalculatedPointTrigonometryDTO pointDTO) {
        Cos cos = Mockito.mock(Cos.class);

        Sin sin = Mockito.mock(Sin.class);
        Sec sec = Mockito.mock(Sec.class);

        Csc csc = Mockito.mock(Csc.class);

        Tan tan = Mockito.mock(Tan.class);
        Cot cot = Mockito.mock(Cot.class);

        Ln ln = new Ln();
        Log log10 = new Log(10, ln);
        Log log2 = new Log(2, ln);
        Log log5 = new Log(5, ln);
        Log log3 = new Log(3, ln);


        when(cos.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getCosValue());
        when(sin.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getSinValue());
        when(sec.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getSecValue());
        when(csc.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getCscValue());
        when(tan.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getTanValue());
        when(cot.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getCotValue());

        BigFunction system = new BigFunction(sec, tan, csc, sin, cos, cot, log3, log5, log10, log2);

        double result = system.compute(pointDTO.getArgument(), accuracy);
        assertEquals(pointDTO.getTargetValue(), round(result, roundDigits));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction2lvl(CalculatedPointTrigonometryDTO pointDTO) {
        Cos cos = Mockito.mock(Cos.class);

        Sin sin = Mockito.mock(Sin.class);
        Sec sec = Mockito.mock(Sec.class);

        Csc csc = new Csc(sin);
        Tan tan = new Tan(sin, cos);
        Cot cot = new Cot(sin, cos);

        Ln ln = new Ln();
        Log log10 = new Log(10, ln);
        Log log2 = new Log(2, ln);
        Log log5 = new Log(5, ln);
        Log log3 = new Log(3, ln);


        when(cos.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getCosValue());
        when(sin.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getSinValue());
        when(sec.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getSecValue());

        BigFunction system = new BigFunction(sec, tan, csc, sin, cos, cot, log3, log5, log10, log2);

        double result = system.compute(pointDTO.getArgument(), accuracy);
        assertEquals(pointDTO.getTargetValue(), round(result, roundDigits));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction3lvl(CalculatedPointTrigonometryDTO pointDTO) {
        Cos cos = Mockito.mock(Cos.class);

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


        when(cos.compute(pointDTO.getArgument(), accuracy)).thenAnswer(invocation -> pointDTO.getCosValue());
        when(cos.compute(pointDTO.getArgument() - Math.PI / 2, accuracy)).thenAnswer(invocation -> pointDTO.getSinValue());

        BigFunction system = new BigFunction(sec, tan, csc, sin, cos, cot, log3, log5, log10, log2);

        double result = system.compute(pointDTO.getArgument(), accuracy);
        assertEquals(pointDTO.getTargetValue(), round(result, roundDigits));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction4lvl(CalculatedPointTrigonometryDTO pointDTO) {
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
