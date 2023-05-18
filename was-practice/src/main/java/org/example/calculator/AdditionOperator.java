package org.example.calculator;

public class AdditionOperator implements NewArithmeticOperator {

    @Override
    public boolean supports(String operator) {
        return operator.equals("+");
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toint() + operand2.toint();
    }
}
