import org.junit.Assert;
import org.junit.Test;
import src.JsonWorker;

import java.io.*;

public class JsonWorkerTests {

    @Test
    public void FirstTests() throws IOException {


        JsonWorker worker = new JsonWorker("C:\\Users\\user\\Desktop\\test.JSON");

        File f = new File("C:\\Users\\user\\Desktop\\test.JSON");

        FileReader reader = new FileReader("C:\\Users\\user\\Desktop\\test.JSON");

        String buf;

        BufferedReader breader = new BufferedReader(reader);

        String strres = breader.readLine();

        strres = strres + breader.readLine();
        strres = strres + breader.readLine();
        strres = strres + breader.readLine();

        String res = "{" +
                "{\"game\":\"g\",\"secondPlayerLanguage\":\"sl\",\"firstPlayer\":\"f\",\"firstPlayerLanguage\":\"l\",\"sdkVer\":834,\"secondPlayer\":\"hui\",\"gameResult\":1}" +
                "{\"game\":\"g\",\"secondPlayerLanguage\":\"sl\",\"firstPlayer\":\"f\",\"firstPlayerLanguage\":\"l\",\"sdkVer\":834,\"secondPlayer\":\"hui\",\"gameResult\":1}" +
                "}";

        Assert.assertSame(strres, res);
    }
}