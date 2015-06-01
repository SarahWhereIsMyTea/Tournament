package src;

import java.io.IOException;

/**
 * Created by user on 01.06.2015.
 */
interface IDataBaseWorker {

    void AddGameResult(String game, String firstPlayer, String firstPlayerLanguage, String secondPlayer, String secondPlayerLanguage, int gameResult, int sdkVer) throws IOException;
}
