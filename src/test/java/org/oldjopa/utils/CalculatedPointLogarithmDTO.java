package org.oldjopa.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculatedPointLogarithmDTO {
    private double argument;
    private double targetValue;
    private double lnValue;
}
