import reader.FileManager;


import java.io.IOException;

public class FileContentFilteringApp {
    static String path = "src/main/resources/in1.txt";
    public static void main(String[] args) throws IOException {
       FileManager.readFile(new String[]{path});

    }
}
