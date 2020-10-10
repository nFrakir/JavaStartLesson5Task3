package com.gmail.seliverstova.hanna;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your money and press Enter");
        String[] userMoney = sc.nextLine().split("[,.]");
        if (userMoney[0].length() > 9) {
            System.out.println("Can't process more than million dollars");
        } else {
            System.out.println(getMoneyString(userMoney));
        }
        sc.close();
    }

    private static String getMoneyString(String[] userMoney) {

        String[] array1 = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
                "thirteen", "fourteen","fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] array2 = {null, null, "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] array3 = {"million", "thousand", "hundred"};
        String tempNumberString;
        int tmp;
        String resultStr = "";

        int[] parts = new int[3];
        int dollars = Integer.valueOf(userMoney[0]);
        int cents = getCents(userMoney);

        parts[0] = dollars / 1_000_000;
        parts[1] = dollars % 1_000_000 / 1_000;
        parts[2] = dollars % 1_000;

        for (int i = 0; i < parts.length; i++) {
            tempNumberString = "";
            tmp = parts[i];
            if (tmp != 0) {
                tempNumberString = (tmp / 100 == 0) ? "" : array1[tmp / 100] + " " + array3[2] + " ";
                if (tmp % 100 < 20) {
                    tempNumberString += array1[tmp % 100] + " ";
                } else {
                    tempNumberString += array2[tmp % 100 / 10] + " " + array1[tmp % 100 % 10] + " ";
                }
                tempNumberString = (i == 2) ? tempNumberString : tempNumberString + array3[i] + " ";
            }
            resultStr += tempNumberString;
        }

        tempNumberString = "";
        if (cents == 0) {
            tempNumberString = "zero ";
        } else  if (cents < 20) {
            tempNumberString = array1[cents] + " ";
        } else {
            tempNumberString = array2[cents / 10] + " " + array1[cents % 10] + " ";
        }

        return "You have: " + resultStr + "dollars " + tempNumberString + "cents";
    }

        private static int getCents(String[] userMoney) {
            int cents = 0;
            if (userMoney.length > 1) {
                cents = Integer.valueOf(userMoney[1].substring(0, userMoney[1].length()));
                cents = ((userMoney[1].length() == 1)) ? cents * 10 : cents;
            }
            return cents;
        }
}
