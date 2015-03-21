public class CodeValidationChecker {

    String _fileName;
    String _lang;

    public CodeValidationChecker(String fileName) {
        _fileName = fileName;
        _lang = DetermineLang(fileName);
    }

    public static String DetermineLang(String fileName){
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        if(extension == "cpp")
            return "Cpp";
        if(extension == "cs")
            return "CSharp";
        if(extension == "rb")
            return "Ruby";

        return "unknown";

    }

    public boolean IsCodeCorrect()
    {
        return true;
    }
}
