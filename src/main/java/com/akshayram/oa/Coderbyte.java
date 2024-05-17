package com.akshayram.oa;

public class Coderbyte {

    // Java program to find missing digit x in expression
    static String result = "";

    static String MissingDigit(String exp) {
        int res = 0;
        String x;

        // Split the expression to extract operands, operator, and resultant
        String[] expParts = exp.split(" ");
        String first_operand = expParts[0];
        String operator = expParts[1];
        String second_operand = expParts[2];
        String resultant = expParts[expParts.length - 1];

        // If x is present in resultant
        if (resultant.indexOf('x') != -1) {
            x = resultant;
            int firstOperand = Integer.parseInt(first_operand);
            int secondOperand = Integer.parseInt(second_operand);

            switch (operator) {
                case "+":
                    res = firstOperand + secondOperand;
                    break;
                case "-":
                    res = firstOperand - secondOperand;
                    break;
                case "*":
                    res = firstOperand * secondOperand;
                    break;
                default:
                    res = firstOperand / secondOperand;
            }
        }
        // If x is present in operands
        else {
            int resultantValue = Integer.parseInt(resultant);

            // If x is in the first operand
            if (first_operand.indexOf('x') != -1) {
                x = first_operand;
                int secondOperandValue = Integer.parseInt(second_operand);

                switch (operator) {
                    case "+":
                        res = resultantValue - secondOperandValue;
                        break;
                    case "-":
                        res = resultantValue + secondOperandValue;
                        break;
                    case "*":
                        res = resultantValue / secondOperandValue;
                        break;
                    default:
                        res = resultantValue * secondOperandValue;
                }
            }
            // If x is in the second operand
            else {
                x = second_operand;
                int firstOperandValue = Integer.parseInt(first_operand);

                switch (operator) {
                    case "+":
                        res = resultantValue - firstOperandValue;
                        break;
                    case "-":
                        res = firstOperandValue - resultantValue;
                        break;
                    case "*":
                        res = resultantValue / firstOperandValue;
                        break;
                    default:
                        res = firstOperandValue / resultantValue;
                }
            }
        }

        String resString = Integer.toString(res);
        int k = 0;
        for (char c : x.toCharArray()) {
            if (c == 'x') {
                result = String.valueOf(resString.charAt(k));
                break;
            } else {
                k++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // input expression
        String exp = "1x0 * 12 = 1200";
        System.out.println(MissingDigit(exp));
    }


}
