package org.oldjopa.MathFunctions;

import lombok.AllArgsConstructor;
import lombok.Getter;
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

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;


public class TrigonometricFunctionsTest {

    private static List<CalculatedPointTrigonometryDTO> valueList;

    @BeforeAll
    static void buildValueList(){
        CsvReader.readData("test_values_trigonometry.csv", valueList);
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
    void testFunction1lvl(CalculatedPointTrigonometryDTO pointDTO, double accuracy) {
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


        when(cos.compute(pointDTO.getArgument(), 10)).thenAnswer(invocation -> pointDTO.getCosValue());
        when(sin.compute(pointDTO.getArgument(), 10)).thenAnswer(invocation -> pointDTO.getSinValue());
        when(sec.compute(pointDTO.getArgument(), 10)).thenAnswer(invocation -> pointDTO.getSecValue());
        when(csc.compute(pointDTO.getArgument(), 10)).thenAnswer(invocation -> pointDTO.getCscValue());
        when(tan.compute(pointDTO.getArgument(), 10)).thenAnswer(invocation -> pointDTO.getTanValue());
        when(cot.compute(pointDTO.getArgument(), 10)).thenAnswer(invocation -> pointDTO.getCotValue());

        BigFunction system = new BigFunction(sec, tan, csc, sin, cos, cot, log3, log5, log10, log2);

        double result = system.compute(pointDTO.getArgument(), 10);
        assertEquals(pointDTO.getTargetValue(), result);
    }


}