package src;

public class DataBaseWorker {


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

}
