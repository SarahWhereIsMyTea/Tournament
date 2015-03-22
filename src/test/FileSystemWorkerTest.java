package test;

import org.junit.Assert;
import org.junit.Test;
import src.FileSystemWorker;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileSystemWorkerTest {

    @Test
    public void testGetDelimer() throws Exception {
        Assert.assertEquals("\\", FileSystemWorker.GetDelimer());
    }

    @Test
    public void testGetUsername() throws Exception {
        Path path = Paths.get("c:\\Dir1\\Games\\Cross\\user1\\ruby\\Cross.rb");

        Assert.assertEquals("user1", FileSystemWorker.GetUsername(path));
    }

    @Test
    public void testGetLang() throws Exception {
        Path path = Paths.get("c:\\Dir1\\Games\\Cross\\user1\\ruby\\Cross.rb");

        Assert.assertEquals("ruby", FileSystemWorker.GetLang(path));
    }
}