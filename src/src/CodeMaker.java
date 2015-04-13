package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class CodeMaker {

    String _fileName;
    String _lang;

    public CodeMaker(String fileName, String lang) {
        _fileName = fileName;
        _lang = lang;
    }

    public boolean Make() {
        if(_lang.equals("Cpp"))
            MakeCpp();
        if(_lang.equals("CSharp"))
            MakeCSharp();
        if(_lang.equals("Ruby"))
            MakeRake();
        return true;
    }

    public boolean CheckFileCreation()
    {
        File file = new File(_fileName);

        return file.exists();
    }

    private void MakeRake() {

    }

    private void MakeCSharp() {

    }

    private void MakeCpp() {
        String make = "make -f makeplus";
        Runtime rt = Runtime.getRuntime();

        try {
            rt.exec(make, null, new File(_fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
