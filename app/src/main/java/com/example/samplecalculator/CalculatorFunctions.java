package com.example.samplecalculator;

public class CalculatorFunctions {
    private double operand;
    private double memOperand;
    private String memOperator;
    private double calcMemory;
    private static final String add = "+";
    private static final String subtract = "-";
    private static final String multiply = "*";
    private static final String divide = "/";
    private static final String clear = "C";
    private static final String memoryClear = "MC";
    private static final String memoryAdd = "M+";
    private static final String memorySubtract = "M-";
    private static final String memoryRecall = "MR";
    private static final String squareRoot = "√";
    private static final String squared = "x²";
    private static final String invert = "1/x";
    private static final String signToggle = "+/-";
    private static final String sine = "sin";
    private static final String cosine = "cos";
    private static final String tangent = "tan";
    public static final String equal = "=";

    MainActivity ma = new MainActivity();
    public String inputOp = "";



    /*
     * Initialize the variables on start
     * */
    public CalculatorFunctions() {
        operand = 0;
        memOperand = 0;
        memOperator = "";
        calcMemory = 0;
    }

    /*
     * Sets the operand to be used in the calculation
     * */
    public void setOperand(double operand) {
        this.operand = operand;
    }

    /*
     * Get the operand previously used
     * */
    public String getOperand() {

        return memOperator;
    }

    /*
     * Returns the results to the user
     * */
    public double getResult() {
        return operand;
    }

    /*
     * Sets the calculated value into memory
     * */
    public void setMemory(double calculatorMemory) {
        calcMemory = calculatorMemory;
    }

    /*
     * Retrieves the value stored in memory
     * */
    public double getMemory() {
        return calcMemory;
    }

    /*
     * Converts the operand to a string to be displayed
     * */
    public String toString() {
        return Double.toString(operand);
    }

    /*
     * Function to handle button presses
     * Handles the memory functions
     * Handles the scientific calculator options
     * */
    protected double instantCalculatorOperation(String operator) {

        if (operator.equals(clear)) {
            operand = 0;
            memOperator = "";
            memOperand = 0; //
            calcMemory = 0;
            //ma.historyView.setText(null);
        } else if (operator.equals(memoryClear)) {
            calcMemory = 0;
        } else if (operator.equals(memoryAdd)) {
            calcMemory = calcMemory + operand;
        } else if (operator.equals(memorySubtract)) {
            calcMemory = calcMemory - operand;
        } else if (operator.equals(memoryRecall)) {
            operand = calcMemory;
        } else if (operator.equals(squareRoot)) {
            ma.stack.add(squareRoot);
            operand = Math.sqrt(operand);
        } else if (operator.equals(squared)) {
            ma.stack.add(squared);
            operand = operand * operand;
        } else if (operator.equals(invert)) {
            if (operand != 0) {
                operand = 1 / operand;
            }
        } else if (operator.equals(signToggle)) {
            operand = -operand;
        } else if (operator.equals(sine)) {
            ma.stack.add(sine);
            operand = Math.sin(Math.toRadians(operand));
            Math.toRadians(operand);
        } else if (operator.equals(cosine)) {
            ma.stack.add(cosine);
            operand = Math.cos(Math.toRadians(operand));
            Math.toRadians(operand);
        } else if (operator.equals(tangent)) {
            ma.stack.add(tangent);
            operand = Math.tan(Math.toRadians(operand));
            Math.toRadians(operand);
        } else {
            calculatorOperation();
            System.out.println(memOperator);
            memOperator = operator;
            memOperand = operand;
        }
        return operand;
    }

    /*
     * Function to handle button presses
     * Handles the addition, subtraction, multiplication and division functions
     * */
    protected void calculatorOperation() {

        if (memOperator.equals(add)) {
            operand = memOperand + operand;
            inputOp = memOperator;
            //System.out.println(inputOp);
            ma.stack.add(inputOp);
        } else if (memOperator.equals(subtract)) {
            inputOp = subtract;
            //System.out.println(inputOp);
            ma.stack.add(subtract);
            operand = memOperand - operand;
        } else if (memOperator.equals(multiply)) {
            inputOp = multiply;
            //System.out.println(inputOp);
            ma.stack.add(multiply);
            operand = memOperand * operand;
        } else if (memOperator.equals(divide)) {
            if (operand != 0) {
                inputOp = divide;
                //System.out.println(inputOp);
                operand = memOperand / operand;
                ma.stack.add(divide);
            }
        }
    }

}