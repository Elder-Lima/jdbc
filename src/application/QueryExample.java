package application;

import db.DB;

import java.sql.*;

public class QueryExample {
    public static void main(String[] args) {

        Connection conn = null;
        // Mais indicado usar o PreparedStatement
        Statement st = null;
        ResultSet rs = null;

        try {
            // conexão com o Banco
            conn = DB.getConnection();

            // instanciando o statement, preparar uma consulta
            st = conn.createStatement();

            // resultado da consulta
            rs = st.executeQuery("select * from department");

            // percorrer os dados
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
