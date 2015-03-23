package src;

public class Main {

    public static void main(String[] args) {

        if(args.length != 2)    // принимаем 3 аргумента - имя пользователя, игру и имя файла кода
            return;

        Tournament tournament = new Tournament(args[0], args[1], args[2]);

        int gameResult = tournament.Game();

        return;
    }
}
