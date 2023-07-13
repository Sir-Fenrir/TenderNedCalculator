package org.example;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/*
    Current constraints:
        - int only
        - five operators {+,-,*,/,^}
        - calculation of form: value operator value, no whitespaces
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
    private String[] subExpressions;
    private int result;
    private char lastOperator;

    public Calculator(){

    }

    public Calculator(String expression){
        this.expression = expression;
        parse();
    }

    public void newExpression(String expression){
        this.expression = expression;
        parse();
    }

    public int getResult(){
        return result;
    }

    public void calculate(){
        if(subExpressions == null) {
            result = Integer.parseInt(expression);
            return;
        }
        for(int i=subExpressions.length-1; i > 0; i--){
            result = performOperation(Integer.parseInt(subExpressions[i-1]), Integer.parseInt(subExpressions[i]), lastOperator);
        }
    }

    private int performOperation(int input1, int input2, char operator){
        int output = 0;
        switch (operator) {
            case '+' -> output = input1 + input2;
            case '-' -> output = input1 - input2;
            case '*' -> output = input1 * input2;
            case '/' -> output = input1 / input2;
            case '^' -> output = (int) Math.pow(input1,input2);
            default -> {
                System.out.println("Error: No valid operator provided. Input1: " + input1 + " Input2: " + input2 + " Operator: " + operator );
            }
        }
        return output;
    }

    private void parse(){
        //Locate operator, exit if no valid operator found
        if(expression.contains("+")){
            subExpressions = expression.split("\\+");
            lastOperator = '+';
        }
        else if (expression.contains("-")){
            subExpressions = expression.split("-");
            lastOperator = '-';
        }
        else if (expression.contains("*")){
            subExpressions = expression.split("\\*");
            lastOperator = '*';
        }
        else if (expression.contains("/")){
            subExpressions = expression.split("/");
            lastOperator = '/';
        }
        else if (expression.contains("^")){
            subExpressions = expression.split("\\^");
            lastOperator = '^';
        }
        else {
            lastOperator = 'E';//Indicating ERROR
        }
    }
}