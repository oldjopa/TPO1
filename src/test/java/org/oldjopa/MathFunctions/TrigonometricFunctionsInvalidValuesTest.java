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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class TrigonometricFunctionsInvalidValuesTest {

    private static final List<CalculatedPointTrigonometryDTO> invalidValueList = new ArrayList<>();

    private static final double accuracy = 0.00001;
    private static final int roundDigits = 5;

    @BeforeAll
    static void buildValueList() {
        CsvReader.readDataTrigonometry("test_invalid_values_trigonometry.csv", invalidValueList);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    static Stream<Arguments> provideArguments() {
        return invalidValueList.stream().map(Arguments::of);
    }


    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction2lvlInvalidValues(CalculatedPointTrigonometryDTO pointDTO) {
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

        assertThrows(ArithmeticException.class, () -> system.compute(pointDTO.getArgument(), accuracy));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction3lvlInvalidValues(CalculatedPointTrigonometryDTO pointDTO) {
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

        assertThrows(ArithmeticException.class, () -> system.compute(pointDTO.getArgument(), accuracy));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testFunction4lvlInvalidValues(CalculatedPointTrigonometryDTO pointDTO) {
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

        assertThrows(ArithmeticException.class, () -> system.compute(pointDTO.getArgument(), accuracy));
    }

}
