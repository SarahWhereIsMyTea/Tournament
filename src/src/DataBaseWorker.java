package src;

import java.sql.*;

public class DataBaseWorker {

    private String _server;
    private String _table;
    private String _user;
    private String _password;

    public DataBaseWorker(String server, String table, String user, String password) {

        _server = server;
        _table = table;
        _user = user;
        _password = password;
    }

    public void WriteGameResult(String firstPlayer, String firstPlayerLang, String secondPlayer, String secondPlayerLang, int result) {
        //Write to DB Here
        //Эту часть делает Худяков=)
        //Худя, жги)
    }

}
