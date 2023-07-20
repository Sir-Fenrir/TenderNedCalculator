package org.example;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/*
    Current constraints:
        - int only
        - five operators {+,-,*,/,^}
        - allows brackets
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

