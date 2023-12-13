package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import bsh.EvalError;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static final double allowedRootDeviation = Math.pow(10, -9);

    static String equation = "10+x=15";
    static ArrayList<String> roots = new ArrayList<>(Arrays.asList("5"));

    public static String selectedEquations = "";

    public static void main(String[] args) throws EvalError {

        addCorrectEquationAndRootToDB(equation, roots , allowedRootDeviation);
        searchEquationByRoot(new String[] {"5", "3"});
        System.out.println(selectedEquations);

    }

        private static void addCorrectEquationAndRootToDB(String equation, ArrayList<String> roots, double allowedDiff) throws EvalError {

        if (Calculator.isEquationCorrect(equation)) {

            var checkedRoots = correctRoots(roots, allowedDiff, equation);

            String dbStatement = "";

            String insertCorrectEquationData = "INSERT INTO test (equation, roots) VALUES (?, null)";

            try {
                Connection connection = DriverManager.getConnection(ConnectionData.jdbcUrl, ConnectionData.username, ConnectionData.password);
                PreparedStatement statement = connection.prepareStatement(insertCorrectEquationData);

                if(checkedRoots.size() != 0){

                    for (var root: checkedRoots) {
                        dbStatement += root;
                        dbStatement += " ";
                    }

                    insertCorrectEquationData = "INSERT INTO test (equation, roots) VALUES (?, ?)";

                    statement = connection.prepareStatement(insertCorrectEquationData);
                    statement.setString(2, dbStatement);
                }

                statement.setString(1, equation);
                statement.executeUpdate();
                connection.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void searchEquationByRoot(String[] root){

        String selectEquationByRoot = "SELECT equation from test WHERE roots iLIKE concat('%', ?, '%')";

        try{
            Connection connection = DriverManager.getConnection(ConnectionData.jdbcUrl, ConnectionData.username, ConnectionData.password);

            PreparedStatement statement = connection.prepareStatement(selectEquationByRoot);

            for (var number: root) {

                statement.setString(1, number);
                ResultSet result = statement.executeQuery();

                while(result.next()) {
                    selectedEquations += result.getString("equation");
                    selectedEquations += " ";
                }
            }

            connection.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private static ArrayList<String> correctRoots(ArrayList<String> root, double allowedDiff, String equation) throws EvalError {

        for(var i = 0; i < root.size(); i++){
            var equationPartsDiff = Double.valueOf(Calculator.calculateEquatPartsDiff(equation, root.get(i)).toString());

            if(equationPartsDiff > allowedDiff){
                root.remove(root.get(i));
            }
        }

        return root;
    }

}
