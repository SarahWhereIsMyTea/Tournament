package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Game {

    String _game;
    String _player;
    String _lang;
    DataBaseWorker _dbworker;
    String _judge;


    public Game(String game, String player, String lang, DataBaseWorker dbworker, String judge){
        _game = game;
        _player = player;
        _lang = lang;
        _dbworker = dbworker;
        _judge = judge;
    }

    public void OrganizeGame()
    {
        try {
            Files.walk(Paths.get(GlobalData.getInstance().Root + _game)).forEach(filePath -> {
                Do(filePath);
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
    private void Do(Path filePath) {
        if(!filePath.endsWith("runme.sh"))
            return;

        String firstPlayer = _player;
        String firstPlayerLang = _lang;

        String secondPlayer = GlobalData.getInstance().GetUsername(filePath);
        String secondPlayerLang = GlobalData.getInstance().GetLang(filePath);

        String commands[] = {_judge, firstPlayer + " " + firstPlayerLang + " " + secondPlayer + " " + secondPlayerLang};
        Runtime rt = Runtime.getRuntime();

        int gameResult;
        try {
            Process proc = rt.exec(commands);
            gameResult = proc.exitValue();
            _dbworker.InsertInTable(firstPlayer, firstPlayerLang, secondPlayer, secondPlayerLang, gameResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
