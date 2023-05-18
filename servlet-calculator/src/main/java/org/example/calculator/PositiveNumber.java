package org.example.calculator;

public class PositiveNumber {
    private final int value;

    public PositiveNumber(int value) {
        validater(value);
        this.value = value;
    }

    private void validater(int value) {
        if (isNegativeNumber(value)) {
            throw new IllegalArgumentException("0또는 음수를 전달할 수 없습니다.");
        }

    }

    private boolean isNegativeNumber(int value) {
        return value <= 0;
    }

    public int toint(){
        return value;
    }
}
