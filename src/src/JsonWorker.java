package src;
import org.json.simple.JSONObject;

import java.io.*;

/**
 * Created by user on 04.05.2015.
 */
public class JsonWorker implements IDataBaseWorker {

    public JsonWorker() throws IOException {

        File f = new File("GamesResults.json");

        if(f.exists())
        {
            f.delete();
        }

        File F = new File("GamesResults.json");

        RandomAccessFile file = new RandomAccessFile("GamesResults.json", "rw");

        file.writeBytes("{\r\n[\r\n]\r\n}");

        file.close();
    }

    public void AddGameResult(String game, String firstPlayer, String firstPlayerLanguage, String secondPlayer, String secondPlayerLanguage, int gameResult, int sdkVer) throws IOException {


        JSONObject res = makeResultNote(game, firstPlayer, firstPlayerLanguage, secondPlayer, secondPlayerLanguage, gameResult, sdkVer);

        String insertedInFile = res.toString();

        RandomAccessFile file = new RandomAccessFile("GamesResults.json", "rw");

        file.skipBytes((int)file.length() -4);

        file.writeBytes(insertedInFile + "\r\n" + "]\r\n}");

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
