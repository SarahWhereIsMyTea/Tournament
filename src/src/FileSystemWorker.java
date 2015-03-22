package src;

import java.io.File;
import java.nio.file.Path;

public class FileSystemWorker {
    public static String GetExtension(String path){
        int sepPos = path.lastIndexOf(File.separator);
        String nameAndExt = path.substring(sepPos + 1, path.length());
        int dotPos = nameAndExt.lastIndexOf(".");
        return dotPos!=-1 ? nameAndExt.substring(dotPos + 1):"";
    }
}
