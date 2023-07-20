package org.example;

import java.util.ArrayList;
import java.util.List;

class Calculator{
    private String expression;
    private List<String> subExpressions;
    private int result;

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
        int subResult = Integer.parseInt(subExpressions.get(0));
        String currentExpression;
        for(int i = 0; i < subExpressions.size(); i++){
            currentExpression = subExpressions.get(i);
            if(isOperator(currentExpression)){
                subResult = performOperation(Integer.parseInt(subExpressions.remove(i-2)) , Integer.parseInt(subExpressions.remove(i-2)) ,currentExpression.charAt(0));//index for both is i-2 because after remove, next element moves down by 1
                subExpressions.set(i-2, Integer.toString(subResult));
                i = i-2; //we have removed 2 elements from the list, so I should go down by 2 as well
            }
        }
        result = subResult;
    }

    private int performOperation(int input1, int input2, char operator){
        int output;
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
        if(expression == null || expression.length() == 0)
            throw new RuntimeException("No expression provided.");
        separateTokens();
        List<String> outputQueue = new ArrayList<>();
        List<String> stack = new ArrayList<>();
        //formats subExpressions into postfix notation
        for (String currentExpression : subExpressions) {
            if (isOperator(currentExpression)) {
                if(currentExpression.charAt(0) == '(')
                    stack.add(currentExpression);
                else if(currentExpression.charAt(0) == ')'){
                    while(stack.get(stack.size()-1).charAt(0) != '(')
                        outputQueue.add(stack.remove(stack.size()-1));
                    stack.remove(stack.size()-1);
                }
                else if(stack.size() > 0){
                    while(greaterOperatorPrecedence(currentExpression.charAt(0), stack.get(stack.size()-1).charAt(0)))
                        outputQueue.add(stack.remove(stack.size()-1));
                    stack.add(currentExpression);
                }
                else stack.add(currentExpression);
            }
            else outputQueue.add(currentExpression);
        }
        while(stack.size() > 0)
            outputQueue.add(stack.remove(stack.size()-1));
        subExpressions = outputQueue;
    }

    private void separateTokens(){
        //separate numbers and operators into separate strings
        char lastChar = expression.charAt(0);
        char currentChar;
        int expressionIndex = 0;
        if(lastChar == '('){
            subExpressions.add(expression.substring(0,1));
            expressionIndex = 1;
        }
        for(int i = 1; i<expression.length();i++){
            currentChar = expression.charAt(i);
            //If an operator is found, add previous number to subExpressions, and add operator to subExpressions
            if(isOperator(currentChar) && !isOperator(lastChar)){
                subExpressions.add(expression.substring(expressionIndex, i));
                subExpressions.add(expression.substring(i,i+1));
                expressionIndex = i+1;
            }
            else if(isOperator(lastChar) && currentChar == '(' || lastChar == ')' && isOperator(currentChar)) //edge cases to make sure brackets get processed properly
            {
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
        return character == '+' || character == '-' || character == '*' || character == '/' || character == '^' || character == '(' || character == ')';
    }

    private boolean isOperator(String string){
        if(string.length() > 1)
            return false;
        return isOperator(string.charAt(0));
    }

    //returns true if operator2 has greater precedence than operator1, does not check brackets
    private boolean greaterOperatorPrecedence(char operator1, char operator2){
        boolean precedence = false;
        switch(operator1){
            case '+', '-' -> precedence = operator2 == '*' || operator2 == '/' || operator2 == '^';
            case '*', '/' -> precedence = operator2 == '^';
            case '^' -> precedence = false;
        }
        return precedence;
    }
}
