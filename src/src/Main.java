package src;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if(args.length != 3)    // принимаем 3 аргумента - имя пользователя, игру и имя файла кода
            return;

        Tournament tournament = new Tournament(args[0], args[1], args[2]);

        try {
            int gameResult = tournament.Game();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}
