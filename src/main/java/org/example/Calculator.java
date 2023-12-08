package org.example;

public class Calculator {

    public static boolean IsEquationCorrect(String equation) {

        var equationCharArray = equation.trim().toCharArray();
        String patterns = "+)-*/(=x. ";

        for (char item: equationCharArray) {

                if (!Character.isDigit(item)) {

                    if (!patterns.contains(String.valueOf(item))) {
                        return false;
                    }
                }
            }

        return CheckEqualityOfParenthneses(equationCharArray);
    }

    private static boolean CheckEqualityOfParenthneses(char[] equation){

        int quantityOfParenthesis = 0;

        for (char item: equation) {

            if(item == '('){
                quantityOfParenthesis++;
            }

            if(item == ')'){
                quantityOfParenthesis--;
            }
        }

        if(quantityOfParenthesis == 0){
            return true;
        }
        return false;
    }
}
