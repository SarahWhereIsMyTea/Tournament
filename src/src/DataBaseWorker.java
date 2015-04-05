package src;

public class DataBaseWorker {


    DbConnection(String dbName,String userName,String tableName, String password)
    {
        private String _dbName;
        private String _tableName;
        private String _password;
        private String _userName;
        private Connection _con;
        private Statement _statement;

        DbConnection(String dbName,String userName,String tableName, String password)
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

        private int GetIntDataFromTable(String playerName, String data) throws SQLException {

        String select = "select "+ data + " from " + _tableName + " where player = '" + playerName + "'";

        ResultSet rs = _statement.executeQuery(select);

        rs.next();

        int count = rs.getInt(data);

        return count;
    }

        public void InsertInTable(String firstPlayer, String secondPlayer, String language, String winPlayerName) throws SQLException {

        int gamesCount = GetIntDataFromTable(winPlayerName, "games");

        String strCount = Integer.toString(gamesCount + 1);

        _statement.execute("update " + _tableName + " set games = " + strCount + " where player = '" + winPlayerName + "'");

        int winsCount = GetIntDataFromTable(winPlayerName, "wins");

        String strWinsCount = Integer.toString(winsCount + 1);

        _statement.execute("update " + _tableName + " set wins = " + strWinsCount + " where player = '" + winPlayerName + "'");

        if( firstPlayer != winPlayerName) {
            gamesCount = GetIntDataFromTable(firstPlayer, "games");
            strCount = Integer.toString(gamesCount + 1);
            _statement.execute("update " + _tableName + " set games = " + strCount + " where player = '" + firstPlayer + "'");
        }
        else {
            gamesCount = GetIntDataFromTable(secondPlayer, "games");
            strCount = Integer.toString(gamesCount + 1);
            _statement.execute("update " + _tableName + " set games = " + strCount + " where player = '" + secondPlayer + "'");
        }
    }

        protected void finalize() throws Throwable {
        _con.close();
    }

    }

}
