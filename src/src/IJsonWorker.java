package src;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IJsonWorker {

    void AddGameResult(String Game, String firstPlayer, String firstPlayerLanguage, String secondPlayer, String secondPlayerLanguage, int gameResult,int sdkVer) throws IOException;
}
