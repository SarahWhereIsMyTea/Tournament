package src;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileSystemWorker {

    public static String GetParam(String keyName) throws IOException {
        Wini ini = new Wini(new File("tournamentset.ini"));

        return ini.get("General", keyName);
    }

    public static String GetDelimer(){
        if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) //если windows
            return "\\";

        if(System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0 ||
                System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0) //если linux / unix
            return "/";

        return "/";
    }

    static String _delimer = GetDelimer();

    public static String GetLang(Path path){
        return path.getName(path.getNameCount() - 2).toString();
    }

    public static String GetUsername(Path path) {
        return path.getName(path.getNameCount() - 3).toString();
    }

    public static String GetRoot() throws IOException {
        return GetParam("Root");
    }
}
