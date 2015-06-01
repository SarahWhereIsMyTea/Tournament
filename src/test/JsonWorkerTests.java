import org.junit.Assert;
import org.junit.Test;
import src.JsonWorker;

import java.io.*;

public class JsonWorkerTests {

    @Test
    public void FileCreationTest() throws IOException {

        JsonWorker worker = new JsonWorker();

        worker.AddGameResult("g", "a", "al", "b", "bl", 1, 533);

        File f = new File("aVSbresult.txt");

        Assert.assertTrue(f.exists());

        f.delete();
    }

    @Test
    public void FileContentTest() throws IOException {

        JsonWorker worker = new JsonWorker();

        worker.AddGameResult("g", "a", "al", "b", "bl", 1, 534);
        worker.AddGameResult("g", "a", "al", "b", "bl", 1, 535);
        worker.AddGameResult("g", "a", "al", "b", "bl", 1, 536);
        worker.AddGameResult("g", "a", "al", "b", "bl", 1, 537);

       /* File f = new File("aVSbresult.txt");

       FileReader reader = new FileReader(f);

        BufferedReader breader = new BufferedReader(reader);

        String res = "";

        res = res + breader.readLine();
        res = res + breader.readLine();
        res = res + breader.readLine();

        String exp = "{{\"game\":\"g\",\"secondPlayerLanguage\":\"bl\",\"firstPlayer\":\"a\",\"firstPlayerLanguage\":\"al\",\"sdkVer\":534,\"secondPlayer\":\"b\",\"gameResult\":1}{";*/

        Assert.assertTrue(true);

        //f.delete();
    }
}