package src;

import org.ini4j.Wini;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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
    int SDKVer;
    String UserPath;
    String DBServer;
    String DBName;
    String DBTable;
    String DBLogin;
    String DBPassword;
    String Judge;

    public GlobalData(){
        Root = GetParam("Root");
        SDKPath = GetParam("SDKPath").replace("%Root%", Root);
        SDKVer = DetermineSDKVer();
        UserPath = GetParam("UserPath").replace("%Root%", Root);
        DBServer = GetParam("DBServer");
        DBName = GetParam("DBName");
        DBTable = GetParam("DBTable");
        DBLogin = GetParam("DBLogin");
        DBPassword = GetParam("DBPassword");
        Judge = GetParam("Judge").replace("%Root%", Root);;
    }

    private int DetermineSDKVer() {
        try {
            FileInputStream fis = new FileInputStream(SDKPath + "../version");
            Scanner sc = new Scanner(fis);
            return sc.nextInt();

        } catch (Exception ex) {
            return 0;
        }
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

    public static String GetLang(String fileName){
        Path path = Paths.get(fileName);
        return path.getName(path.getNameCount() - 2).toString();
    }

    public static String GetUsername(String fileName) {
        Path paths = Paths.get(fileName);
        return paths.getName(paths.getNameCount() - 3).toString();
    }

    public static String GetFileName(String path) {
        return Paths.get(path).getFileName().toString();
    }
}
