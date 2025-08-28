package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    public static void main(String[] args) {

        Connection conn = null;

        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            // Lógica de transação
            conn.setAutoCommit(false);

            st = conn.prepareStatement("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
            int rows1 = st.executeUpdate();

//            int x = 1;
//            if (x < 2) {
//                throw new SQLException("Fake error");
//            }

            st = conn.prepareStatement("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
            int rows2 = st.executeUpdate();

            // Lógica de transação
            conn.commit();

            System.out.println("Rows 1 = " + rows1);
            System.out.println("Rows 2 = " + rows2);

        }
        catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            }catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Cause by: " + e1.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
