package com.helper.work.calculator;

public class Adder extends CalculateBase implements MathProcessing {

    @Override
    public void calculate() {
        setResult(getLeftVal() + getRightVal());
    }

    @Override
    public double doCalculation(double leftVal, double rightVal) {
        setLeftVal(leftVal);
        setRightVal(rightVal);
        calculate();
        return getResult();
    }
    @Override
    public String getKeyword() {
        return "ADD";
    }
}
