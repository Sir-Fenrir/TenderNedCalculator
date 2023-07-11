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
        calculation = userInput.nextLine().trim();


        calculate(calculation);


    }

    public static void calculate(String calculation){
        int input1, input2, output;
        char operator;
        String[] values;

        //Locate operator, exit if no valid operator found
        if(calculation.contains("+")){
            operator = '+';
            values = calculation.split("\\+");
        }
        else if (calculation.contains("-")){
            operator = '-';
            values = calculation.split("-");
        }
        else if (calculation.contains("*")){
            operator = '*';
            values = calculation.split("\\*");
        }
        else if (calculation.contains("/")){
            operator = '/';
            values = calculation.split("/");
        }
        else if (calculation.contains("^")){
            operator = '^';
            values = calculation.split("\\^");
        }
        else {
            System.out.println("No valid operator provided.");
            return;
        }

        //Perform calculation
        input1 = Integer.parseInt(values[0]);
        input2 = Integer.parseInt(values[1]);

        switch (operator) {
            case '+' -> output = input1 + input2;
            case '-' -> output = input1 - input2;
            case '*' -> output = input1 * input2;
            case '/' -> output = input1 / input2;
            case '^' -> output = (int) Math.pow(input1,input2);
            default -> {
                System.out.println("Error: Unexpected exit.");
                output = 0;
            }
        }
        System.out.print(input1 + " " + operator + " " + input2 + " = " + output);
    }

}