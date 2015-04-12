package src;

import java.sql.*;

public class DataBaseWorker {

        private String _dbName;
        private String _tableName;
        private String _password;
        private String _userName;
        private Connection _con;
        private Statement _statement;

        DataBaseWorker(String dbName, String tableName, String userName, String password)
        {
            _tableName = tableName;
            _password = password;
            _userName = userName;
            _dbName = dbName;

            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                String url = "jdbc:mysql://localhost/" + _dbName;
                String name = userName;
                String accessPassword = password;
                try {
                    _con = DriverManager.getConnection(url, name, accessPassword);
                    _statement = _con.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        public void InsertInTable(String firstPlayer, String firstPlayerLanguage, String secondPlayer, String secondPlayerLanguage, int gameResult) throws SQLException {

            String sql = "DELETE FROM `" + _tableName + "` WHERE player1 = '" + firstPlayer + "' AND lang1 = '" + firstPlayerLanguage + "' AND player2 = '" + secondPlayer + "' AND lang2 = '" + secondPlayerLanguage + "'";
            _statement.execute("DELETE FROM `" + _tableName + "` WHERE player1 = '" + firstPlayer + "' AND lang1 = '" + firstPlayerLanguage + "' AND player2 = '" + secondPlayer + "' AND lang2 = '" + secondPlayerLanguage + "'");
            _statement.execute("DELETE FROM `" + _tableName + "` WHERE player1 = '" + secondPlayer + "' AND lang1 = '" + secondPlayerLanguage + "' AND player2 = '" + firstPlayer + "' AND lang2 = '" + firstPlayerLanguage + "'");

            _statement.execute("INSERT INTO `" + _tableName + "` VALUES ('" + secondPlayer + "', '" + secondPlayerLanguage + "', '" + firstPlayer + "', '" + firstPlayerLanguage + "', " + gameResult + ")");
            _statement.execute("INSERT INTO `" + _tableName + "` VALUES ('" + firstPlayer + "', '" + firstPlayerLanguage + "', '" + secondPlayer + "', '" + secondPlayerLanguage + "', " + gameResult * (-1) + ")");
        }

        protected void finalize() throws Throwable {
        _con.close();
    }

}
