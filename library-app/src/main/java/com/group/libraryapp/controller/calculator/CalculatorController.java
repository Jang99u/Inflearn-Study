package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorListRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorRequest;
import com.group.libraryapp.dto.calculator.response.CalculatorResponse;
import com.group.libraryapp.dto.calculator.response.DateReponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalculatorController {
    @GetMapping("/add")
    public int addTwoNumbers(@RequestParam int number1, @RequestParam int number2) {
        return number1 + number2;
    }

    @GetMapping("/add2")
    public int addTwoNumbers2(CalculatorAddRequest calculatorAddRequest) {
        return calculatorAddRequest.getNumber1() + calculatorAddRequest.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest calculatorMultiplyRequest) {
        return calculatorMultiplyRequest.getNumber1() * calculatorMultiplyRequest.getNumber2();
    }

    @GetMapping("/api/v1/calc")
    public CalculatorResponse calculateNumbers(CalculatorRequest calculatorRequest) {
        return new CalculatorResponse(calculatorRequest.getNumber1() + calculatorRequest.getNumber2(),
                calculatorRequest.getNumber1() - calculatorRequest.getNumber2(),
                calculatorRequest.getNumber1() * calculatorRequest.getNumber2());
    }

    @PostMapping("/api/v1/calc")
    public Integer calculateNumberList(@RequestBody CalculatorListRequest calculatorListRequest) {
        List<Integer> numList = calculatorListRequest.getNumbers();
        Integer result = 0;
        for(int i = 0; i < numList.size(); i++) {
            result += numList.get(i);
        }
        return result;
    }

    @GetMapping("/api/v1/day-of-the-week")
    public DateReponse dayOfTheWeek(@RequestParam String date) {
        return new DateReponse(date);
    }
}
