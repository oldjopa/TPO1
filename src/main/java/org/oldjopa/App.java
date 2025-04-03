package org.oldjopa;

import org.oldjopa.math.BigFunction;
import org.oldjopa.math.logarithm.Ln;
import org.oldjopa.math.logarithm.Log;
import org.oldjopa.math.trigonometry.*;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        CsvWriter csvWriter = new CsvWriter();
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
//        csvWriter.writeToCsv("tan.csv", tan, -10, 10, 0.1);
//        csvWriter.writeToCsv("cos.csv", cos, -10, 10, 0.1);
//        csvWriter.writeToCsv("sec.csv", sec, -10, 10, 0.1);
//        csvWriter.writeToCsv("log.csv", log2, -10, 10, 0.1);
        csvWriter.writeToCsv("func.csv", system, -10, 10, 0.0001);

    }
//
//
//            csvWriter.write(
//                   sin,
//                    "Sin.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(2*Math.PI),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );
//            csvWriter.write(
//                    cos,
//                    "Cos.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(2*Math.PI),
//                    BigDecimal.valueOf(0.1),
//                    5
//            );
//            csvWriter.write(
//                    tan,
//                    "Tan.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(0),
//                    BigDecimal.valueOf(0.1),
//                    5
//            );
//            csvWriter.write(
//                    sec,
//                    "Sec.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(Math.PI),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );
//            csvWriter.write(
//                    csc,
//                    "Csc.csv",
//                    BigDecimal.valueOf(-2*Math.PI),
//                    BigDecimal.valueOf(Math.PI),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );
//
//            csvWriter.write(
//                    ln,
//                    "Ln.csv",
//                    BigDecimal.valueOf(0),
//                    BigDecimal.valueOf(5),
//                    BigDecimal.valueOf(0.1),
//                    5
//            );
//            csvWriter.writeToCsv("");
//            csvWriter.write(
//                    system,
//                    "Function.csv",
//                    BigDecimal.valueOf(-3*Math.PI),
//                    BigDecimal.valueOf(2),
//                    BigDecimal.valueOf(0.01),
//                    5
//            );


}