package reader;

import properties.Options;
import properties.TypeString;
import statictic.Statistic;
import statictic.StatisticImpl;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class FileManager {
    private static final Parser parser = new Parser();
    private final Options options;
    private static final Map<TypeString, BufferedWriter> writers = new HashMap<>();
    private static final Statistic stats = new StatisticImpl();

    public FileManager(Options options) {
        this.options = options;
    }

    //Отлов сделать java.lang.NullPointerException
    public void workWithFiles() {
        for (String file : options.getFiles()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    TypeString type = parser.defineType(str);
                    BufferedWriter writer = getWriter(type);
                    if (options.isStats()) {
                        stats.addValue(type, str);
                    }
                    writer.write(str);
                    writer.newLine();
                }
            } catch (IOException exp) {
                System.out.printf("The reading file path %s is incorrect%n", file);
            }
        }

        closeAll();

        if (options.isStats()) {
            switch (options.getTypeStat()) {
                case FULL -> stats.getFullStats();
                case SHORT -> stats.getShortStats();
            }
        }
    }

    private  BufferedWriter getWriter(TypeString type) throws IOException {
        if (!writers.containsKey(type)) {
            StringBuilder builder = new StringBuilder();
            if (options.getName() != null) {
                builder.append(options.getName());
            }
            builder.append(type.getFileName());

            Path basePath = Path.of(builder.toString());

            if (options.getPath() != null) {
                basePath = options.getPath().resolve(basePath);
            }
             try {
                 BufferedWriter writer = new BufferedWriter(new FileWriter(basePath.toFile(), options.isAppend()));
                 writers.put(type, writer);
             } catch (IOException exp) {
                 System.out.printf("The directory for the recording file was not found." +
                         "The %s file will be placed in the current directory.%n", type);
                 BufferedWriter writer = new BufferedWriter(new FileWriter(builder.toString(), options.isAppend()));
                 writers.put(type, writer);
             }
        }
        return writers.get(type);
    }

    private void closeAll() {
        writers.values().forEach(writer -> {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        });
    }
}
