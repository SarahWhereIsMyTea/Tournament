package src;

public class CodeMaker {

    String _fileName;
    String _lang;

    public CodeMaker(String fileName, String lang) {
        _fileName = fileName;
        _lang = lang;
    }

    public boolean Make() {
        if(_lang == "Cpp")
            MakeCpp();
        if(_lang == "CSharp")
            MakeCSharp();
        if(_lang == "Rake")
            MakeRake();
        return true;
    }

    private void MakeRake() {

    }

    private void MakeCSharp() {

    }

    private void MakeCpp() {

    }

}
