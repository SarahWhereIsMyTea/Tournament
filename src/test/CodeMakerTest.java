import org.junit.Assert;
import org.junit.Test;
import src.CodeMaker;

import static org.junit.Assert.*;

public class CodeMakerTest {

@Test
public void CheckCreationTest()
{
    CodeMaker maker = new CodeMaker("C:\\Users\\user\\Desktop\\Tournament\\make\\", "Cpp");

    maker.Make();

    Assert.assertTrue(maker.CheckFileCreation());
}

}