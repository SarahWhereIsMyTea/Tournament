package src;
import org.json.simple.JSONObject;

import java.io.*;

/**
 * Created by user on 04.05.2015.
 */
public class JsonWorker implements IJsonWorker {

    public void AddGameResult(String game, String firstPlayer, String firstPlayerLanguage, String secondPlayer, String secondPlayerLanguage, int gameResult, int sdkVer) throws IOException {

        String fileName = firstPlayer + "VS" + secondPlayer + "result.txt";

        File f = new File(fileName);

        f.createNewFile();

        JSONObject res = makeResultNote(game, firstPlayer, firstPlayerLanguage, secondPlayer, secondPlayerLanguage, gameResult, sdkVer);

        String insertedInFile = res.toString();

        RandomAccessFile file = new RandomAccessFile(fileName, "rw");

        file.skipBytes((int)file.length() - 1);

        file.writeBytes("{\r\n" + insertedInFile + "\r\n" + "}");

        file.close();

    }

    private JSONObject makeResultNote(String game, String firstPlayer, String firstPlayerLanguage, String secondPlayer, String secondPlayerLanguage, int gameResult, int sdkVer){

        JSONObject res = new JSONObject();

        res.put("game", game);
        res.put("firstPlayer", firstPlayer);
        res.put("firstPlayerLanguage", firstPlayerLanguage);
        res.put("secondPlayer", secondPlayer);
        res.put("secondPlayerLanguage",secondPlayerLanguage);
        res.put("gameResult", gameResult);
        res.put("sdkVer", sdkVer);

        return res;
    }

}
