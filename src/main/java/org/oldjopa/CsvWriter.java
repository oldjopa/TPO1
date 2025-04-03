package org.oldjopa;

import org.oldjopa.math.MathFunction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.DoubleStream;

public class CsvWriter {
    public void writeToCsv(String filename, MathFunction function, final double start,
                                  final double end, final double step)
            throws IOException {

        try (FileWriter writer = new FileWriter("doc/"+filename)) {
            for (double i = start; i < end; i+=step) {
                writer.write(i + "," + function.compute(i, 0.001) + "\n");
            }
        }
    }
}
