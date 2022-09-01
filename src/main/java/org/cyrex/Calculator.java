package org.cyrex;

import java.util.Locale;
import java.util.Scanner;

/**
 * Метод умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотрите пример)! Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
 * Метод умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
 * Метод должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.
 * Метод умеет работать только с целыми числами.
 * Метод умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II метод должен выбросить исключение и прекратить свою работу.
 * При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.
 * При вводе пользователем неподходящих чисел метдод выбрасывает исключение и завершает свою работу.
 * При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, метод выбрасывает исключение и завершает свою работу
 * Результатом операции деления является целое число, остаток отбрасывается.
 * Результатом работы метода с арабскими числами могут быть отрицательные числа и ноль. Результатом работы метода с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
 */


public class Calculator {
    public static void main(String[] args) throws Exception {
        while (true) {
            CalculatorHelper calculatorHelper = new CalculatorHelper();
            System.out.print("Введите выражение: ");
            Scanner scanner = new Scanner(System.in); //Scanner myObj = new Scanner(System.in);
            String input = scanner.nextLine();        //String input = myObj.nextLine();
            System.out.println("Ответ: " + calculatorHelper.calc(input));
        }
    }
}

class CalculatorHelper {
    public String calc(String input) throws Exception {
        String[] splitText = input.split(" ");
        boolean isRomanNumeral = false; //Boolean rome = false;
        int num1, num2; //int letter1, letter2;
        String operation;

        int countNumbers = 0; //int countLetters;

        for (int i = 0; i < splitText.length; i += 2) {
            try {
                Integer.parseInt(splitText[i]);
            } catch (NumberFormatException e) {
                isRomanNumeral = true;
            } finally {
                countNumbers++;  //emptyLine
            }
        }


        if (countNumbers != 2) throw new Exception("В выражении должно быть 2 числа");
        // if (countLetters == 1) throw new Exception("letter format do not consist");

        num1 = getInteger(splitText[0]);
        operation = splitText[1];//emptyLine
        num2 = getInteger(splitText[2]);
        int result = getResult(num1, num2, operation);
        String output;


        //if (result > 10 || result <= 0) throw new Exception("Arab letter result should be between 0 and 10");

        if (isRomanNumeral) {
            if (result <= 0) throw new Exception("В римской системе счисления нет отрицательных чисел и нуля");
            else output = getRomeNumber(result);
        } else {
            output = String.valueOf(result);
        }
        return output;
    }

    //    public Integer getLetter(String letter) throws Exception {
    public int getInteger(String input) throws Exception {
        int integer;

        try {
            integer = Integer.parseInt(input);
        } catch (Exception e) {
            switch (input.toLowerCase(Locale.ROOT)) {
                case "i":
                    integer = 1;
                    break;
                case "ii":
                    integer = 2;
                    break;
                case "iii":
                    integer = 3;
                    break;
                case "iv":
                    integer = 4;
                    break;
                case "v":
                    integer = 5;
                    break;
                case "vi":
                    integer = 6;
                    break;
                case "vii":
                    integer = 7;
                    break;
                case "viii":
                    integer = 8;
                    break;
                case "ix":
                    integer = 9;
                    break;
                case "x":
                    integer = 10;
                    break;
                default:
                    throw new Exception("Введенное римское число больше 10");
//                    throw new Exception("Arab letter > 10");
            }
        }
        if (integer > 10) throw new Exception("Введенное арабское число > 10"); //emptyLin
        return integer;
    }

    public String getRomeNumber(int num) throws Exception {
        String romeNum;
        switch (num) {
            case 1:
                romeNum = "I";
                break;
            case 2:
                romeNum = "II";
                break;
            case 3:
                romeNum = "III";
                break;
            case 4:
                romeNum = "IV";
                break;
            case 5:
                romeNum = "V";
                break;
            case 6:
                romeNum = "VI";
                break;
            case 7:
                romeNum = "VII";
                break;
            case 8:
                romeNum = "VIII";
                break;
            case 9:
                romeNum = "IX";
                break;
            case 10:
                romeNum = "X";
                break;
            default:
                throw new Exception("The result > 10");
        }
        return romeNum;
    }

    public Integer getResult(int letter1, int letter2, String s) throws Exception {
        int result;
        switch (s) {
            case "/":
                result = letter1 / letter2;
                break;
            case "+":
                result = letter1 + letter2;
                break;
            case "-":
                result = letter1 - letter2;
                break;
            case "*":
                result = letter1 * letter2;
                break;
            default:
                throw new Exception("Wrong operation format");
        }

        return result;
    }
}