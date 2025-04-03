package org.oldjopa.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {
    public static void readData(String filename, List<CalculatedPointTrigonometryDTO> valueList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                double argument = Double.parseDouble(values[0]);
                double targetValue = Double.parseDouble(values[1]);
                double cosValue = Double.parseDouble(values[2]);
                double sinValue = Double.parseDouble(values[3]);
                double tanValue = Double.parseDouble(values[4]);
                double cotValue = Double.parseDouble(values[5]);
                double secValue = Double.parseDouble(values[6]);
                double cscValue = Double.parseDouble(values[7]);

                valueList.add(new CalculatedPointTrigonometryDTO(argument, targetValue, cosValue, sinValue, tanValue, cotValue, secValue, cscValue));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
