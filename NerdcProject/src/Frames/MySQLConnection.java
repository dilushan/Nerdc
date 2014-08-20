//This is a Singleton class for Connection for database
package Frames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MySQLConnection {

    public static MySQLConnection SQLConnection = null;
    private Connection con;
    private Statement statement;

    private MySQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");                                                                             // this will load the MySQL driver, each DB has its own driver
            // setup the connection with the DB.
            con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root");
            statement = con.createStatement();
            statement.executeUpdate("create database if not exists NERDC");
            statement.executeUpdate("use NERDC");

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static synchronized MySQLConnection getInstance() {
        if (SQLConnection == null) {
            SQLConnection = new MySQLConnection();
        }
        return SQLConnection;
    }

    public Connection getConnection() {
        return con;
    }

    public ResultSet queryStatement(String str) throws SQLException {
        return statement.executeQuery(str);
    }

    public int updateStatement(String str) throws SQLException {
        return SQLConnection.statement.executeUpdate(str);
    }

}
