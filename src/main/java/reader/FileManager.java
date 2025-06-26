package reader;

import properties.TypeString;

import java.io.*;


public class FileManager {
    private static Parser parser = new Parser();
    //private static FileWriter strings;
    private static BufferedWriter strings;
    //private static FileWriter integers;
    private static BufferedWriter integers;
    //private static FileWriter floats;
    private static BufferedWriter floats;


    public static void readFile(String[] pathsFile) throws IOException {
        for (String path : pathsFile) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
                while (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    TypeString type = parser.defineType(str);

                    switch (type) {
                        case STRING -> {
                            if (strings == null) {
                                strings = new BufferedWriter(new FileWriter(type.getFileName()));
                            }
                            parser.parseString(str);
                            strings.write(str);
                            strings.newLine();

                        }
                        case INTEGER -> {
                            if (integers == null) {
                                integers = new BufferedWriter(new FileWriter(type.getFileName()));
                            }
                            parser.parseInt(str);
                            integers.write(str);
                            integers.newLine();
                        }
                        case FLOAT -> {
                            if (floats == null) {
                                floats = new BufferedWriter(new FileWriter(type.getFileName()));
                            }
                            parser.parseFloat(str);
                            floats.write(str);
                            floats.newLine();

                        }
                    }
                }
            } catch (FileNotFoundException exp) {
                System.out.printf("Путь для файла %s указан неверно%n", path);
            }
        }
        strings.close();
        integers.close();
        floats.close();
    }

    public static void writeStringsFile(String str, FileWriter strings) throws IOException {
        strings.write(str);
    }

    public static void writeIntegersFile(String str, FileWriter integers) throws IOException {
        integers.write(str);
    }

    public static void writeFloatsFile(String str, FileWriter floats) {

    }

}
