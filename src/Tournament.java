import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

public class Tournament {
    String _userName;
    String _game;

    //много параметров, может, их в отдельный класс?
    String _fullPath;
    String _tempPath;
    String _tableCaption;
    String _judgePath;

    public Tournament(String userName, String game) {
        _userName = _userName;
        _game = game;

        try {
            LoadIni();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadIni() throws IOException {
        Wini ini = new Wini(new File("tournamentset.ini"));
        _fullPath = ini.get("Paths", "FullPath");
        _tempPath = ini.get("Paths", "TempPath");
        _tableCaption = ini.get("DBSettings", "TableCaption");
        _judgePath = ini.get("ExternalLinks", "JudgePath");
    }
}
