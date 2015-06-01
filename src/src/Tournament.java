package src;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class Tournament {
    String _userName;
    String _game;
    String _sourceFile;
    String _destFile;
    String _lang;

    IDataBaseWorker _dataBaseWorker;

    public Tournament(String userName, String game, String fileName){
        _userName = userName;
        _game = game;
        _sourceFile = fileName;
        _lang = DetermineLang(_sourceFile);

        if(_lang.equals("unknown"))
            throw new InvalidPathException(_sourceFile, "Invalid file extension. Could not determine program language");
    }

    public static String DetermineLang(String fileName){
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        if(extension.contains("cpp"))
            return "Cpp";
        if(extension.contains("cs"))
            return "CSharp";
        if(extension.contains("rb"))
            return "Ruby";

        return "unknown";
    }

    /*
    * Возвращать из функции Game() будем целое число
    * 0 - всё ок
    * 1 - ошибка проверки корректности кода
    * --может что-то еще придется добавить
    * */

    public int Game() throws IOException {
        CopyFiles();
        if(!CheckCode())
            return 1;

        return MakeGame();
    }

    private void CopyFiles() throws IOException {
        _destFile = GlobalData.getInstance().UserPath.replaceAll("%lang%", _lang).
                                                      replaceAll("%user%", _userName).
                                                      replaceAll("%game%", _game);

        FileUtils.cleanDirectory(new File(_destFile));

        Files.copy(Paths.get(_sourceFile), Paths.get(_destFile + GlobalData.GetFileName(_sourceFile)));

        FileUtils.copyDirectory(new File(GlobalData.getInstance().SDKPath.replace("%lang%", _lang)), new File (_destFile));
    }

    private int MakeGame() throws IOException {
        InitDataBaseWorker();
        String judge = GlobalData.getInstance().Judge;
        Game game = new Game(_game, _userName, _lang, _dataBaseWorker, judge);
        game.OrganizeGame();

        return 0;
    }

    private void InitDataBaseWorker() throws IOException {
        String server = GlobalData.getInstance().DBName;
        String table = GlobalData.getInstance().DBTable;
        String login = GlobalData.getInstance().DBLogin;
        String password = GlobalData.getInstance().DBPassword;
        _dataBaseWorker = new JsonWorker();
    }

    private boolean CheckCode() {
        CodeMaker codeMaker = new CodeMaker(_destFile, _lang);
        if(!codeMaker.Make())
            return false;

        return true;
    }
}
