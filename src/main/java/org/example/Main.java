package org.example;

import java.sql.*;

import bsh.EvalError;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static String equation = "1+x=2";
    static String root = "1";

    static final double allowedRootDeviation = Math.pow(10, -9);

    public static void main(String[] args) throws EvalError {

        addCorrectEquationAndRootToDB(equation, root, allowedRootDeviation);

    }

    private static void addCorrectEquationAndRootToDB(String equation, String root, double allowedDiff) throws EvalError {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/Equation";
        String username = "postgres";
        String password = "4321";

        if (Calculator.isEquationCorrect(equation)) {

            var equationPartsDiff = Double.valueOf(Calculator.calculateEquatPartsDiff(equation, root).toString());

            Double rootParam = null; ;
            String insertCorrectEquationData = "INSERT INTO test (equation, roots) VALUES (?, null)";

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                PreparedStatement statement = connection.prepareStatement(insertCorrectEquationData);

                if(equationPartsDiff <= allowedDiff){
                    rootParam = Double.valueOf(root);
                    insertCorrectEquationData = "INSERT INTO test (equation, roots) VALUES (?, ?)";

                    statement = connection.prepareStatement(insertCorrectEquationData);
                    statement.setDouble(2, rootParam);
                }

                statement.setString(1, equation);
                statement.executeUpdate();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
