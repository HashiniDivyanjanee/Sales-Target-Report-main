package Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        connecToDatabase();
    }

    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private void connecToDatabase() throws SQLException {
//        String server = "FP-SERVER-PC";   
//        String server = "JANAKA-LAP";
        String server = "localhost";
//        String server = "iPC-01";
//        String server = "DESKTOP-KT0RRIP";
//        String database = "data";
        String database = "fsms-data";
//        String database = "ds";
        String username = "PosUser";
        String password = "Lhd12345";
        String port = "3307";
//        String server = "DS-SVR-PC";
//        String username = "PosUser";
//        String password = "MasterPos1212";

        connection = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
    
        if (connection == null){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connecToDatabase();
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
