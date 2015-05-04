import org.junit.Assert;
import org.junit.Test;
import src.IDataBaseWorker;
import src.JsonWorker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

/**
 * Created by user on 14.04.2015.
 */
public class DataBaseWorkerTests {

    @Test
    public void WriteGameResultCallTest() throws SQLException, IOException {

        JsonWorker worker = new JsonWorker("test.JSON");

        try {
            worker.AddGameResult("g", "f", "l", "sn", "sl", 1, 834);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
