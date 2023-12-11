package org.example;

import java.sql.*;

import bsh.EvalError;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static String equation = "2*((x+5+x)+5)=10";
    static String root = "7";

    static double allowedRootDeviation = Math.pow(10, -9);

    public static void main(String[] args) throws EvalError {

    }

    private static void addCorrectEquationAndRootToDB(String equation, String root) {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/Equation";
        String username = "postgres";
        String password = "4321";

        if (Calculator.isEquationCorrect(equation)) {

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                System.out.println("Connected");
                String sql = "INSERT INTO test (equation) VALUES (?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, equation);

                statement.executeUpdate();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
