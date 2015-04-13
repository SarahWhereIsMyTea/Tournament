package test;

import org.junit.Assert;
import org.junit.Test;
import src.Game;
import src.IDataBaseWorker;

import java.nio.file.Paths;

import static org.mockito.Mockito.*;

public class GameTest {

    @Test
    public void testOrganizeGame() throws Exception {

        IDataBaseWorker mockedWorker = mock(IDataBaseWorker.class);

        Game game = new Game("game","Player1", "Cpp", mockedWorker, "..\\src\\test" );

        game.Do(Paths.get("runme"));

        boolean res = verify(mockedWorker).InsertInTable(any(String.class), any(String.class), any(String.class), any(String.class), any(Integer.class),any(Integer.class));

        Assert.assertTrue(res);
    }
}
