package com.group.libraryapp.dto.calculator.request;

import java.util.ArrayList;
import java.util.List;

public class CalculatorListRequest {
    private List<Integer> numbers = new ArrayList<>();

    public List<Integer> getNumbers() {
        return numbers;
    }
}
