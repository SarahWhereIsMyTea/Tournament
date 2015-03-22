package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Game {

    String _player;
    DataBaseWorker _dbworker;
    String _judge;


    public Game(String player, DataBaseWorker dbworker, String judge){
        _player = player;
        //_dbworker = dbworker;
        _judge = judge;
    }

    public void OrganizeGame()
    {
        try {
            Files.walk(Paths.get("d:\\AICupEnvironment\\Games\\Cross\\")).forEach(filePath -> {
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
     * 1 - выиграл первый игрок
     * 2 - выиграл второй игрок
     * Может случиться так, что игра не пройдет (кто-то из игроков не уложится во время или еще что-то)
     * тогда 2 варианта - либо Judge сам решает, что ставит техническое поражение кому-то (то есть другой побеждает)
     * либо определяет эту ситуацию и вернет другое значение
     * это позволит записать в БД соответствующую информацию
    */
    private void Do(Path filePath) {
        if(!filePath.endsWith("runme.sh"))
            return;

        String commands[] = {_judge, "user1, user2"};
        Runtime rt = Runtime.getRuntime();

        int gameResult = 0;
        try {
            Process proc = rt.exec(commands);
            gameResult = proc.exitValue();

            _dbworker.WriteGameResult("user1", "user2", gameResult);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
