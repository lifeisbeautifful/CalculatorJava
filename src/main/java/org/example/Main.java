package org.example;

import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String equation = "2*x+5=17";
        addCorrectEquationToDB(equation);
    }

    private static void addCorrectEquationToDB(String equation){

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