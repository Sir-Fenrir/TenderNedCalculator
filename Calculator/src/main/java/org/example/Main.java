package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/*
    Current constraints:
        - int only
        - five operators {+,-,*,/,^}
        - calculation of form: value operator value
 */
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Scanner userInput = new Scanner(System.in);
        System.out.print("Input your calculation: ");
        String calculation;


        //Get input
        calculation = userInput.nextLine();

        Calculator calculator = new Calculator();
        calculator.newExpression(calculation);
        calculator.calculate();
        System.out.println("Result: " + calculator.getResult());


    }


}

 class Calculator{
    private String expression;
    private List<String> subExpressions;
    private int result;
    private char lastOperator;

    public Calculator(){

    }

    public Calculator(String expression){
        this.expression = expression;
        subExpressions = new ArrayList<>();
        removeWhiteSpace();
        parse();
    }

    public void newExpression(String expression){
        this.expression = expression;
        subExpressions = new ArrayList<>();
        removeWhiteSpace();
        parse();
    }

    public int getResult(){
        return result;
    }

    public void calculate(){
        //assumption: if subExpressions == null then expression is a single number
        /*if(subExpressions == null) {
            result = Integer.parseInt(expression);
            return;
        }
        for(int i=subExpressions.size()-1; i > 0; i--){
            result = performOperation(Integer.parseInt(subExpressions.get(i-1)), Integer.parseInt(subExpressions.get(i)), lastOperator);
        }
         */
    }

    private int performOperation(int input1, int input2, char operator){
        int output = 0;
        switch (operator) {
            case '+' -> output = input1 + input2;
            case '-' -> output = input1 - input2;
            case '*' -> output = input1 * input2;
            case '/' -> output = input1 / input2;
            case '^' -> output = (int) Math.pow(input1,input2);
            default -> throw new RuntimeException("Error: No valid operator provided. Input1: " + input1 + " Input2: " + input2 + " Operator: " + operator );
        }
        return output;
    }

    private void parse(){
        separateTokens();

        List<String> outputQueue = new ArrayList<>();
        List<String> stack = new ArrayList<>();
        String currentExpression;
        for(int i = 0; i < subExpressions.size(); i++){
            currentExpression = subExpressions.get(i);
            if(isOperator(currentExpression)){

            }
            else outputQueue.add(currentExpression);
        }
        subExpressions = outputQueue;
    }

    private void separateTokens(){
        //separate numbers and operators
        char lastChar = expression.charAt(0); //for checking negative numbers
        char currentChar;
        int expressionIndex = 0;
        for(int i = 1; i<expression.length();i++){
            currentChar = expression.charAt(i);
            //If an operator is found, add previous number to subExpressions, and add operator to subExpressions
            if(isOperator(currentChar) && !isOperator(lastChar)){ //Situation of both last and current chars being operators can only occur if currentChar is '-' indicating a negative number
                subExpressions.add(expression.substring(expressionIndex, i));
                subExpressions.add(expression.substring(i,i+1));
                expressionIndex = i+1;
            }
            lastChar = currentChar;
        }
        //adds any trailing number
        if(expressionIndex < expression.length())
            subExpressions.add(expression.substring(expressionIndex));
    }

    private void removeWhiteSpace(){
        expression = expression.replaceAll("\\s+", "");
    }

    private boolean isOperator(char character){
        if(character == '+' || character == '-' || character == '*' || character == '/' || character == '^' || character == '(' || character == ')')
            return true;
        else return false;
    }

     private boolean isOperator(String string){
        if(string.length() > 1)
            return false;
        return isOperator(string.charAt(0));
     }
}