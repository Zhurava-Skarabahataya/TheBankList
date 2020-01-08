import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        ConnectorToDB.connectToDB();

        String sql;
        sql = "SELECT account.account as acc , user.name as name, user.surName as sur, user.userid as id\n" +
                "                FROM account INNER JOIN user ON (account.userid=user.userid)\n" +
                "                WHERE user.userid=2;";

        ResultSet resultSet = ConnectorToDB.getStatement(ConnectorToDB.getConnection()).executeQuery(sql);


        printAccountByID();

        printTheRichestUser();
        printAllAccounts();
        printSumOfAccounts();
        resultSet.close();
        ConnectorToDB.closeConnectionToDB();
    }

    public static void printAllAccounts () throws SQLException{
        System.out.println("Printing all the accounts:");
        for (User a:returnListOfAllAccounts()
        ) {
            System.out.println("Id is " + a.userID + ", name is " + a.name + ", surname is " + a.surName +
                    ", account Id is " + a.accountID +", the account is " + a.account);
        }
    }

    public static List<User> returnListOfAllAccounts()throws SQLException{
        List<User> userList = new ArrayList<User>();
        String sql;
        sql = "SELECT  user.name as name, user.surName as sur, user.userid as id, account.account as acc, account.accountid as accid\n" +
                "                FROM account INNER JOIN user ON (account.userid=user.userid)\n" +
                "                ;";
        ResultSet resultSet = ConnectorToDB.getStatement(ConnectorToDB.getConnection()).executeQuery(sql);
        while (resultSet.next()){
            userList.add(new User(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("sur"), resultSet.getInt("accid"),
                    resultSet.getInt("acc")));
        }
        return userList;
    }

    public static void printAccountByID() throws SQLException{
        System.out.println("Print th ID, please.");
        Scanner in = new Scanner(System.in);
        int id = Integer.parseInt(in.next());
        printAccountByID(id);
    }

    public static void printAccountByID(int id) throws SQLException{
        System.out.println("The user with id " + id + " is " + returnAccountByID(id) + ".");
    }

    public static String returnAccountByID(int id) throws SQLException{
        String sql;
        sql = "SELECT account.account as acc , user.name as n, user.surName as sur, account.accountid as accid, user.userid as id\n" +
                "                FROM account INNER JOIN user ON (account.userid=user.userid)\n" +
                "                WHERE user.userid=" + id + ";";

        ResultSet resultSet = ConnectorToDB.getStatement(ConnectorToDB.getConnection()).executeQuery(sql);
        return getUsersData(resultSet);

    }

    public static String getUsersData(ResultSet resultSet) throws SQLException{
        String name=null;
        String surName=null;
        int account=0;
        int accountId =0;
        if(resultSet.next()) {
            name = resultSet.getString("n");
            surName = resultSet.getString("sur");
            account = resultSet.getInt("acc");
            accountId = resultSet.getInt("accid");
        }
        return name + " " + surName + ", account = " + account + ", accountId "+ accountId;

    }

    public static String returnTheRichestUser() throws SQLException{
        String sql;
        sql = "SELECT  name as n, user.userid as id, user.surName as sur, account.account as acc, account.accountid as accid\n" +
                "                FROM account INNER JOIN user WHERE user.userid = account.userid and\n" +
                "                account.account=(SELECT MAX(account) FROM account)\n" +
                "                ;";

        ResultSet resultSet = ConnectorToDB.getStatement(ConnectorToDB.getConnection()).executeQuery(sql);
        return getUsersData(resultSet);

    }

    public static void printTheRichestUser() throws SQLException{
        System.out.println("The richest user is " + returnTheRichestUser()+".");
    }

    public static String getSumOfAccounts() throws SQLException{
        String sql;
        sql = "SELECT  SUM(account) FROM account;";
        ResultSet resultSet = ConnectorToDB.getStatement(ConnectorToDB.getConnection()).executeQuery(sql);
        if (resultSet.next()){
            return resultSet.getString("SUM(account)");
        }
        else return "";
    }

    public static void printSumOfAccounts() throws SQLException{
        System.out.println("The sum of all the accounts is "+ getSumOfAccounts()+".");
    }

}