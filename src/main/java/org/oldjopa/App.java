package org.oldjopa;

import org.oldjopa.math.BigFunction;
import org.oldjopa.math.logarithm.Ln;
import org.oldjopa.math.logarithm.Log;
import org.oldjopa.math.trigonometry.*;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        Cos cos = new Cos();

        Sin sin = new Sin(cos);
        Tan tan = new Tan(sin, cos);
        Sec sec = new Sec(cos);
        Csc csc = new Csc(sin);
        Cot cot = new Cot(sin, cos);

        Ln ln = new Ln();
        Log log10 = new Log(10, ln);
        Log log2 = new Log(2, ln);
        Log log5 = new Log(5, ln);
        Log log3 = new Log(3, ln);


        BigFunction system = new BigFunction(sec, tan, csc, sin, cos, cot, log3, log5, log10, log2);
        System.out.println(system.compute(Math.PI/2, 0.00000001));
        System.out.println(tan.compute(Math.PI/2, 0.00000001));

//        csvWriter.writeToCsv("tan.csv", tan, -10, 10, 0.1);
//        csvWriter.writeToCsv("cos.csv", cos, -10, 10, 0.1);
//        csvWriter.writeToCsv("sec.csv", sec, -10, 10, 0.1);
//        csvWriter.writeToCsv("log.csv", log2, -10, 10, 0.1);
//        csvWriter.writeToCsv("func.csv", system, -10, 10, 0.0001);

    }

}