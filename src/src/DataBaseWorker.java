package src;

import java.sql.*;

public class DataBaseWorker {

    private java.lang.String _server;
    private java.lang.String _table;
    private java.lang.String _user;
    private java.lang.String _password;

    public DataBaseWorker(String server, String table, String user, String password) {

        _server = server;
        _table = table;
        _user = user;
        _password = password;
    }

    private  Connection getDBConnection(java.lang.String DB_DRIVER) {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {

            dbConnection = DriverManager.getConnection(_table, _user,_password);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return dbConnection;
        }

        return dbConnection;
    }

    public void WriteGameResult(String firstPlayer, String firstPlayerLang, String secondPlayer, String secondPlayerLang, int result) {
        //Write to DB Here
        //Эту часть делает Худяков=)
    }

}
