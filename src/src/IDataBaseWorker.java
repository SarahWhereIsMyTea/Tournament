package src;

import java.sql.SQLException;

public interface IDataBaseWorker {
    public void InsertInTable(String firstPlayer, String firstPlayerLanguage, String secondPlayer, String secondPlayerLanguage, int gameResult,int sdkVer) throws SQLException;

}
