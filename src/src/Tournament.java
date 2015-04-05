package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.ini4j.Wini;

public class Tournament {
    String _userName;
    String _game;
    String _fileName;
    String _lang;

    DataBaseWorker _dataBaseWorker;

    public Tournament(String userName, String game, String fileName){
        _userName = userName;
        _game = game;
        _fileName = fileName;

        _lang = DetermineLang(_fileName);
    }

    public static String DetermineLang(String fileName){
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        if(extension == "cpp")
            return "Cpp";
        if(extension == "cs")
            return "CSharp";
        if(extension == "rb")
            return "Ruby";

        return "unknown";
    }

    /*
    * Возвращать из функции Game() будем целое число
    * 0 - всё ок
    * 1 - ошибка проверки корректности кода
    * --может что-то еще придется добавить
    * */

    public int Game() {
        if(!CheckCode())
            return 1;

        return MakeGame();
    }

    private int MakeGame() {
        InitDataBaseWorker();
        String judge = GlobalData.getInstance().Judge;
        Game game = new Game(_game, _userName, _lang, _dataBaseWorker, judge);

        game.OrganizeGame();

        return 0;
    }

    private void InitDataBaseWorker() {
        String server = GlobalData.getInstance().DBServer;
        String table = GlobalData.getInstance().DBTable;
        String login = GlobalData.getInstance().DBLogin;
        String password = GlobalData.getInstance().DBPassword;

        _dataBaseWorker = new DataBaseWorker(server, table, login, password);
    }

    private boolean CheckCode() {
        CodeMaker codeMaker = new CodeMaker(_fileName, _lang);
        if(!codeMaker.Make())
            return false;

        return true;
    }
}
