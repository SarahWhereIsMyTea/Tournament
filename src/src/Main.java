package src;

public class Main {

    public static void main(String[] args) {

        if(args.length != 2)
            return;

        Tournament tournament = new Tournament(args[0], args[1]);

        return;
    }
}
