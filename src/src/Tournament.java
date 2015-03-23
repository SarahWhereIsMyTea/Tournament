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
        String judge = FileSystemWorker.GetParam("Judge");
        Game game = new Game(_game, _userName, _lang, _dataBaseWorker, judge);

        game.OrganizeGame();

        return 0;
    }

    private void InitDataBaseWorker() {
        String server = FileSystemWorker.GetParam("DBServer");
        String table = FileSystemWorker.GetParam("DBTable");
        String login = FileSystemWorker.GetParam("DBLogin");
        String password = FileSystemWorker.GetParam("DBPassword");

        _dataBaseWorker = new DataBaseWorker(server, table, login, password);
    }

    private boolean CheckCode() {
        CodeValidationChecker checker = new CodeValidationChecker(_fileName, _lang);
        if(!checker.IsCodeCorrect())
            return false;

        MakeRunMe();

        return true;
    }

    private void MakeRunMe() { // чет пока не очень понятно, что и как здесь будет(((
        // тут надо сделать вот этот sh

        Path target = Paths.get(FileSystemWorker.GetParam("FullPath").replaceAll("%lang%", _lang).
                                                                      replaceAll("%user%", _userName).
                                                                      replaceAll("%game%", _game)
                                                                      + "RunMe.sh");
        Path source = Paths.get(Paths.get(_fileName).getParent() + "RunMe.sh");

        try {
            Files.move(source, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
