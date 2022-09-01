package org.cyrex;

class Calculator {
    final static int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    final static String[] rom = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String calc(String input) {
        int firstNumber;
        String operator;
        int secondNumber;
        String firstOperand;
        String secondOperand;
        String[] splitInput = input.split(" ");
        if (splitInput.length != 3) throw new RuntimeException("Invalid expression for calculation");
        firstOperand = splitInput[0];
        operator = splitInput[1];
        secondOperand = splitInput[2];
        if (firstOperand.matches("^[MDCLXVI]+$") && secondOperand.matches("^[MDCLXVI]+$")) {
            firstNumber = romanToInt(firstOperand);
            secondNumber = romanToInt(secondOperand);
            int result = calcArabic(firstNumber, operator, secondNumber);
            if (result <= 0) {
                throw new RuntimeException("Roman numbers can't be negative or zero");
            } else {
                return intToRoman(result);
            }
        } else try {
            firstNumber = Integer.parseInt(firstOperand);
            secondNumber = Integer.parseInt(secondOperand);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Both numbers should be arabic or roman");
        }
        return String.valueOf(calcArabic(firstNumber, operator, secondNumber));
    }

    private static int calcArabic(int firstNumber, String operator, int secondNumber) {
        int result;
        if ((firstNumber < 0 || firstNumber > 10) || (secondNumber < 0 || secondNumber > 10)) {
            throw new RuntimeException("Numbers should not be greater than 10");
        } else {
            switch (operator) {
                case "+" -> {
                    result = firstNumber + secondNumber;
                }
                case "-" -> {
                    result = firstNumber - secondNumber;
                }
                case "*" -> {
                    result = firstNumber * secondNumber;
                }
                case "/" -> {
                    result = firstNumber / secondNumber;
                }
                default -> {
                    throw new RuntimeException("Invalid operator");
                }
            }
            return result;
        }
    }

    public static int romanToInt(String s) {
        int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I' -> num = 1;
                case 'V' -> num = 5;
                case 'X' -> num = 10;
                case 'L' -> num = 50;
                case 'C' -> num = 100;
                case 'D' -> num = 500;
                case 'M' -> num = 1000;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }

    public static String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; num > 0; i++)
            while (num >= val[i]) {
                ans.append(rom[i]);
                num -= val[i];
            }
        return ans.toString();
    }
}