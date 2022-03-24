package com.company.splitwise;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Splitwise Code (Input sample): EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        SplitwiseController splitwiseController = new SplitwiseController();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Enter: ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")){
                break;
            }
            String output = splitwiseController.processInput(input);
            System.out.println(output);
        }
    }
}
