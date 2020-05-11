package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baza_uzytkowe
{
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static Connection conn = null;
    private static final String connStr = "jdbc:mysql://localhost:3306/test";
    private static final String conn_user = "root";
    private static final String conn_pass = "";

    public static void polacz_z_baza() throws SQLException, ClassNotFoundException
    {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Blad");
            e.printStackTrace();
            throw e;
        }

        try {
            conn= DriverManager.getConnection(connStr,conn_user,conn_pass);
        } catch (SQLException e) {
            System.out.println("Blad" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void rozlacz() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }
}
