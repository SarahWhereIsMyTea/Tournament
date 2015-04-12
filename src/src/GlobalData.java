package src;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GlobalData {

    public static volatile GlobalData instance;

    public static GlobalData getInstance(){
        if(instance == null)
            synchronized (GlobalData.class){
                if(instance == null)
                    instance = new GlobalData();
            }
        return instance;
    }

    String Root;
    String SDKPath;
    String UserPath;
    String DBServer;
    String DBTable;
    String DBLogin;
    String DBPassword;
    String Judge;
    String Delimer;

    public GlobalData(){
        Root = GetParam("Root");
        SDKPath = GetParam("SDKPath").replace("%Root%", Root);
        UserPath = GetParam("UserPath").replace("%Root%", Root);
        DBServer = GetParam("DBServer");
        DBTable = GetParam("DBTable");
        DBLogin = GetParam("DBLogin");
        DBPassword = GetParam("DBPassword");
        Judge = GetParam("Judge").replace("%Root%", Root);;
        Delimer = GetDelimer();
    }

    private String GetParam(String keyName) {
        Wini ini = null;
        try {
            ini = new Wini(new File("tournamentset.ini"));
        } catch (IOException e) {
            return "Error";
        }

        return ini.get("General", keyName);
    }

    private String GetDelimer(){
        if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) //если windows
            return "\\";

        if(System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0 ||
                System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0) //если linux / unix
            return "/";

        return "/";
    }

    public static String GetLang(Path path){
        return path.getName(path.getNameCount() - 2).toString();
    }

    public static String GetUsername(Path path) {
        return path.getName(path.getNameCount() - 3).toString();
    }

    public static String GetFileName(String path) { return Paths.get(path).getFileName().toString();}
}
