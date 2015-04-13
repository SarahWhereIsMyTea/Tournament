import org.junit.Assert;
import org.junit.Test;
import src.IDataBaseWorker;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

/**
 * Created by user on 14.04.2015.
 */
public class DataBaseWorkerTests {

    @Test
    public void WriteGameResultCallTest() throws SQLException {

        IDataBaseWorker mockedWorker = mock(IDataBaseWorker.class);

        mockedWorker.InsertInTable("A", "php", "B", "Cpp", 1, 2);

        boolean res = verify(mockedWorker).InsertInTable("A", "php", "B", "Cpp", 1, 2);

        Assert.assertTrue(res);
    }
}
