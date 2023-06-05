package DAO.DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MSSQLDBContext implements DBContext {

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)
    DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=false;trustServerCertificate=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MSSQLDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url, userID, password);
    }

    /*Insert your other code right after this comment
    Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "localhost";
    private final String dbName = "personal-expense-management";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "sa";

}
