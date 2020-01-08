import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorToDB {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/thebanklist"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "root";


    public static void closeConnectionToDB() throws SQLException {

        getStatement(getConnection()).close();
        getConnection().close();
    }

    public static void connectToDB() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = getConnection();
        System.out.println("Executing statement...");
        statement = getStatement(connection);
    }

    public static Connection getConnection() throws SQLException{
        System.out.println("Creating database connection...");
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static Statement getStatement(Connection connection) throws SQLException{
        System.out.println("Executing statement...");
        return connection.createStatement();
    }

}
