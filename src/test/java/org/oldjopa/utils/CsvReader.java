package org.oldjopa.utils;

import java.io.*;
import java.util.List;

public class CsvReader {
    public static void readDataTrigonometry(String filename, List<CalculatedPointTrigonometryDTO> valueList) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
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

    public static void readDataLogarithm(String filename, List<CalculatedPointLogarithmDTO> valueList) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                double argument = Double.parseDouble(values[0]);
                double targetValue = Double.parseDouble(values[1]);
                double lnValue = Double.parseDouble(values[2]);

                valueList.add(new CalculatedPointLogarithmDTO(argument, targetValue, lnValue));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
