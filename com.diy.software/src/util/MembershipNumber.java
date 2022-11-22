package util;

import java.util.HashMap;
import java.util.Scanner;

/*
input will be stored in a HashMap, where:
- key: customer's name
- value: membership number
 */

public class MembershipNumber {
    public static void main(String[] args) {
        HashMap<String, Integer> CustomerMembershipNum = new HashMap<>();

        Scanner obj1 = new Scanner(System.in);  //create scanner object for customer's name
        System.out.println("Enter your name:");
        String name = obj1.nextLine();          //reads user input for customer's name

        System.out.println("Enter your Membership number: ");
        Scanner obj2 = new Scanner(System.in);    //creates scanner object for membership number
        int number = obj2.nextInt();              //reads user input for membership number

        CustomerMembershipNum.put(name, number);  //add both user inputs to hashmap

        //converts the number into a string in order to calculate its length
        int LengthOfNumber = Integer.parseInt(String.valueOf(String.valueOf(CustomerMembershipNum.get(name)).length()));

        while (true) {
            if (LengthOfNumber == 8) {
                for (String i : CustomerMembershipNum.keySet()) {
                    System.out.println(i + ", your Membership number is: " + CustomerMembershipNum.get(i));
                    System.exit(1);
                }
            } else {
                System.out.println("the number you entered is incorrect! Please try again");
                System.out.println("Enter your Membership number again: ");
                number = obj2.nextInt();    //reads input
                CustomerMembershipNum.put(name, number);

                LengthOfNumber = Integer.parseInt(String.valueOf(String.valueOf(CustomerMembershipNum.get(name)).length()));
            }
        }
    }
}