package manager;

import properties.Options;
import properties.DataType;
import statistic.Statistic;
import statistic.StatisticImpl;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


public class FileManager {
    private static final DataFilter dataFilter = new DataFilter();
    private final Options options;
    private static final Map<DataType, BufferedWriter> writers = new HashMap<>();
    private static final Statistic stats = new StatisticImpl();

    public FileManager(Options options) {
        this.options = options;
    }


    public void workWithFiles() {

        if (options.getFiles() == null) {
            System.out.println("There are no files to read");
            return;
        }

        for (String file : options.getFiles()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    DataType type = dataFilter.defineType(str);
                    BufferedWriter writer = getWriter(type);
                    if (writer != null) {
                        if (options.isStats()) {
                            stats.addValue(type, str);
                        }
                        writer.write(str);
                        writer.newLine();
                    } else {
                        closeAll();
                        return;
                    }
                }
            } catch (FileNotFoundException exc) {
                System.out.printf("The reading file path %s is incorrect%n", file);
            } catch (IOException exc) {
                System.out.printf("Error %s in file %s", exc.getMessage(), file);
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

    private BufferedWriter getWriter(DataType type) throws IOException {
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
            } catch (FileNotFoundException exc) {
                System.out.printf("The directory for the recording file %s was not found.%n " +
                        "Please check the path or directory availability and restart the program. ", type);
                writers.put(type, null);
            } catch (IOException exc) {
                System.out.printf("Error creating %s a writing file in file %s%n", exc.getMessage(), basePath);
                writers.put(type, null);
            }
        }
        return writers.get(type);
    }

    private void closeAll() {
        writers.values().forEach(writer -> {
            try {
                writer.close();
            } catch (IOException exc) {
                System.err.printf("Error closing file: %s%n", exc.getMessage());
            }
        });
    }
}
