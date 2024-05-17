package com.akshayram.oa;

public class MathChallenge {
    static double evaluateMath(String x) {
        x = x.replace(" ", "") + ")";

        class MathEvaluator {
            private String x;
            private int pos = 0;

            MathEvaluator(String x) {
                this.x = x;
            }

            double expression() {
                double a = primary();
                for (; ; ) {
                    char operator = x.charAt(pos);
                    pos++;
                    if (operator == ')') {
                        return a;
                    }
                    double b = primary();
                    switch (operator) {
                        case '+':
                            a += b;
                            break;
                        case '-':
                            a -= b;
                            break;
                        case '*':
                            a *= b;
                            break;
                        case '/':
                            a /= b;
                            break;
                    }
                }
            }

            double primary() {
                if (x.charAt(pos) == '(') {
                    pos++;
                    double val = expression();
                    pos++;
                    return val;
                }

                int start = pos;
                while (pos < x.length() && (Character.isDigit(x.charAt(pos)) || x.charAt(pos) == '.' || x.charAt(pos) == '-')) {
                    pos++;
                }
                return Double.parseDouble(x.substring(start, pos));
            }
        }

        return new MathEvaluator(x).expression();
    }

    static double MathChallenge(String str) {
        StringBuilder inputString = new StringBuilder(str);
        int leftPar = 0, rightPar;
        double result, finalResult, tempLeftParVal = 0, tempRightParVal;

        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '(') {
                tempLeftParVal = 0;
                leftPar = i;
                if (leftPar > 0 && inputString.charAt(leftPar - 1) != ')' && inputString.charAt(leftPar - 1) != '+' && inputString.charAt(leftPar - 1) != '-' && inputString.charAt(leftPar - 1) != '*' && inputString.charAt(leftPar - 1) != '/') {
                    tempLeftParVal = Double.parseDouble(inputString.charAt(leftPar - 1) + "");
                }
            }

            if (inputString.charAt(i) == ')') {
                tempRightParVal = 0;
                rightPar = i;
                String parString = inputString.substring(leftPar + 1, rightPar);
                result = evaluateMath(parString);

                if (tempLeftParVal != 0) {
                    result *= tempLeftParVal;
                }

                if (rightPar + 1 < inputString.length() && inputString.charAt(rightPar + 1) != '(' && inputString.charAt(rightPar + 1) != '+' && inputString.charAt(rightPar + 1) != '-' && inputString.charAt(rightPar + 1) != '*' && inputString.charAt(rightPar + 1) != '/') {
                    result *= Double.parseDouble(inputString.charAt(rightPar + 1) + "");
                    tempRightParVal = Double.parseDouble(inputString.charAt(rightPar + 1) + "");
                }

                if (tempLeftParVal != 0 && tempRightParVal != 0) {
                    inputString = new StringBuilder(inputString.substring(0, leftPar - 1) + result + inputString.substring(rightPar + 2));
                    i = i - ((rightPar + 2) - (leftPar - 1));
                } else if (tempLeftParVal != 0) {
                    inputString = new StringBuilder(inputString.substring(0, leftPar - 1) + result + inputString.substring(rightPar + 1));
                    i = i - ((rightPar + 1) - (leftPar - 1));
                } else if (tempRightParVal != 0) {
                    inputString = new StringBuilder(inputString.substring(0, leftPar) + result + inputString.substring(rightPar + 2));
                    i = i - ((rightPar + 2) - leftPar);
                } else {
                    inputString = new StringBuilder(inputString.substring(0, leftPar) + result + inputString.substring(rightPar + 1));
                    i = i - ((rightPar + 1) - leftPar);
                }
            }
        }

        finalResult = evaluateMath(inputString.toString());
        return finalResult;
    }

    //reference: https://coderbyte.com/editor/guest:Math%20Challenge:Java
    //reference: https://github.com/MHassaanButt/MathChallenge/blob/main/MathChallenge.js

    public static void main(String[] args) {
        System.out.println(MathChallenge("6*(4/2)+3*1")); // Output: 15.0
        System.out.println(MathChallenge("6/3-1")); // Output: 1.0
    }
}