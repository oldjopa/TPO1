package org.oldjopa.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculatedPointTrigonometryDTO {
    private double argument;
    private double targetValue;
    private double cosValue;
    private double sinValue;
    private double tanValue;
    private double cotValue;
    private double secValue;
    private double cscValue;
}