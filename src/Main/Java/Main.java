import java.sql.*;

public class Main {
    /**
     * JDBC Driver and database url
     */
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/thebanklist"+
                    "?verifyServerCertificate=false"+
                            "&useSSL=false"+
                            "&requireSSL=false"+
                            "&useLegacyDatetimeCode=false"+
                            "&amp"+
                            "&serverTimezone=UTC";


    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectorToDB ct = new ConnectorToDB();
        ct.connectToDB();

        String sql;
        sql = "SELECT name FROM user where userid=2;";

        ResultSet resultSet = ct.getStatement(ct.getConnection()).executeQuery(sql);

        System.out.println("Retrieving data from database...");

        while (resultSet.next()) {
            String userName = resultSet.getString("name");

            System.out.println("Name: " + userName);
        }

        System.out.println("Closing connection and releasing resources...");
        resultSet.close();
        ct.closeConnectionToDB();
    }




}