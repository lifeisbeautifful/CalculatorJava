package org.example;

public class Calculator {

    //Check if equation contains only digits and allowed for calculation characters
    public static boolean isEquationCorrect(String equation) {

        var equationCharArray = equation.replaceAll("\\s", "").toCharArray();
        String allowedChars = "+)-*/(=x. ";

        for (char item: equationCharArray) {

                if (!Character.isDigit(item)) {

                    if (!allowedChars.contains(String.valueOf(item))) {
                        return false;
                    }
                }
            }

        var parenthnesesAreEqual = checkEqualityOfParenthneses(equationCharArray);
        var mathCharsPlacedCorrect = mathCharPlacedCorrect(equationCharArray);

        return parenthnesesAreEqual && mathCharsPlacedCorrect;
    }

    //Check if quantity of ')' sign is equal to quantity of '('
    private static boolean checkEqualityOfParenthneses(char[] equation){

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

    //Check if equation ends with digit, 'x' or ')' sign
    //Check if math signs are not placed one after another
    private static boolean mathCharPlacedCorrect(char[] equation){
        String mathChars = "-+*/=.";
        String exceptChars = "-x";
        String allowedEquationEndChars = "x)";

        var lastEquationChar = equation[equation.length-1];

        if(!Character.isDigit(lastEquationChar) && !allowedEquationEndChars.contains(String.valueOf(lastEquationChar))){
            return false;
        }

        for(int i = 0; i < equation.length-1; i++){

            var IsCurrentCharMath = mathChars.contains(String.valueOf(equation[i]));
            var IsNextCharMath = mathChars.substring(1).contains(String.valueOf(equation[i+1]));

            if(IsCurrentCharMath && IsNextCharMath){
                return false;
            }

            if(equation[i] == equation[i+1] && exceptChars.contains(String.valueOf(equation[i]))){
                return false;
            }
        }

        return true;
    }
}
