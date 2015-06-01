package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Game {

    String _game;
    String _player;
    String _lang;

    IDataBaseWorker _dbworker;
    String _judge;


    public Game(String game, String player, String lang, IDataBaseWorker dbworker, String judge){
        _game = game;
        _player = player;
        _lang = lang;
        _dbworker = dbworker;
        _judge = judge;
    }

    public void OrganizeGame()
    {
        try {
            Files.walk(Paths.get(GlobalData.getInstance().Root + "/Games/" + _game + "/")).forEach(filePath -> {
                try {
                    Do(filePath);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Здесь проверяем, есть ли файл runme.sh
     * Если есть, то вызываем программу Judge
     * Ловим int ответ, расшифровка пока такая:
     * 0 - ничья (а что, очень даже может быть)
     * -1 - выиграл первый игрок
     * 1 - выиграл второй игрок
     * Может случиться так, что игра не пройдет (кто-то из игроков не уложится во время или еще что-то)
     * тогда 2 варианта - либо Judge сам решает, что ставит техническое поражение кому-то (то есть другой побеждает)
     * либо определяет эту ситуацию и вернет другое значение
     * это позволит записать в БД соответствующую информацию
    */
    public void Do(Path filePath) throws SQLException {
        if(!filePath.endsWith("runme"))
            return;

        String firstPlayer = GlobalData.getInstance().UserPath.replace("%lang%", _lang).replace("%game%", _game).replace("%user%", _player) + "runme";

        String secondPlayer = filePath.toString();

        if(firstPlayer.equals(secondPlayer))
            return;

        String commands[] = {_judge, firstPlayer + " " + secondPlayer};
        Runtime rt = Runtime.getRuntime();

        int gameResult = 1;
        try {
            //Process proc = rt.exec(commands);
            //gameResult = proc.exitValue();
           // _dbworker.AddGameResult(GlobalData.GetUsername(firstPlayer), GlobalData.GetLang(firstPlayer), GlobalData.GetUsername(secondPlayer), GlobalData.GetLang(secondPlayer), gameResult, GlobalData.getInstance().SDKVer);
        } catch (Exception e) {        // change to IOException
            e.printStackTrace();
        }
    }
}
