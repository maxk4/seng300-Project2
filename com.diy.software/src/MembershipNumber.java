package util;


import java.util.Map;
import java.util.Scanner;
import java.util.*;

/*
input will be stored in a HashMap, where:
- key: customer's name
- value: membership number
 */

public class MembershipNumber {
	
	
	
	private ArrayList<Integer> MEMBER_NUMBERS;
	
	public MembershipNumber() {
		MEMBER_NUMBERS = new ArrayList<Integer>();
	}
	
    public static void main(String[] args) {
        HashMap<String, Integer> CustomerMembershipNum = new HashMap<>();

        System.out.println("Enter your Membership number: ");
        Scanner obj2 = new Scanner(System.in);    //creates scanner object for membership number
        int number = obj2.nextInt();              //reads user input for membership number

        //CustomerMembershipNum.put(name, number);  //add both user inputs to hashmap

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
                //CustomerMembershipNum.put(name, number);

                //LengthOfNumber = Integer.parseInt(String.valueOf(String.valueOf(CustomerMembershipNum.get(name)).length()));
            }
        }
    }
    
    public void checkMemNum(Integer num) {
    	String LengthString = num.toString();
    	int NumberLength = LengthString.length();
        if (NumberLength == 8) {
            	
        } else {
                System.out.println("the number you entered is incorrect! Please try again");
                System.out.println("Enter your Membership number again: ");

        }
    }
    
    public void addMem(Integer num) {
    	this.MEMBER_NUMBERS.add(num);
    }
    
    public void removeMem(Integer num){
    	this.MEMBER_NUMBERS.remove(num);
    }
}
