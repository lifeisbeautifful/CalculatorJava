import org.example.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    void equationDigitEndIsCorrect(){
        String equation = "2*x+5=17";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }

    @Test
    void equationXSignEndIsCorrect(){
        String equation = "23=2*2+x";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }

    @Test
    void equationMathSignEndIsIncorrect(){
        String equation = "2*x+5=17-";
        Assertions.assertFalse(Calculator.isEquationCorrect(equation));
    }

    @Test
    void equationSpecCharEndIsIncorrect(){
        String equation = "-1.3*5/x=1?";
        Assertions.assertFalse(Calculator.isEquationCorrect(equation));
    }

    @Test
    void plusAndMinusSignsOneByOneInEquationIsCorrect(){
        String equation = "2*x+-x=10";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }

    @Test
    void plusAndMultiplySignsOneByOneInEquationIsIncorrect(){
        String equation = "-1.3+*5/x=1.2";
        Assertions.assertFalse(Calculator.isEquationCorrect(equation));
    }

    @Test
    void minusAndMinusOneByOneInEquationIsIncorrect(){
        String equation = "2*(x+5+x)+5=--10";
        Assertions.assertFalse(Calculator.isEquationCorrect(equation));
    }

    @Test
    void beginningEquationWithMinusIsCorrect(){
        String equation = "-1.3*5/x=1.2";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }

    @Test
    void evenParenthnessesQuantityIsCorrect(){
        String equation = "-1.3*(5+2)/x=1.2";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }

    @Test
    void oddParenthessesQuantityIsIncorrect(){
        String equation = "-1.3*5+2)/x=1.2";
        Assertions.assertFalse(Calculator.isEquationCorrect(equation));
    }

    @Test
    void lettersInEquationIsNotAllowed(){
        String equation = "-1.3*(5+2)/x-y=1.2";
        Assertions.assertFalse(Calculator.isEquationCorrect(equation));
    }

    @Test
    void notMathSignSpecCharInEquationAreNotAllowed(){
        String equation = "-1.3*(5+2)/x#y=1.2";
        Assertions.assertFalse(Calculator.isEquationCorrect(equation));
    }

    @Test
    void equalitySignIsAllowedInEquation(){
        String equation = "3+x=7";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }

    @Test
    void dotSignIsAllowedInEquation(){
        String equation = "4.6 + x = -3";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }

    @Test
    void spacesAreAllowedInequation(){
        String equation = " 4. 6 + x =   -3";
        Assertions.assertTrue(Calculator.isEquationCorrect(equation));
    }
}
