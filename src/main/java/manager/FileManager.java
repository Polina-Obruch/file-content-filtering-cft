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
                    if (options.isStats()) {
                        stats.addValue(type, str);
                    }
                    writer.write(str);
                    writer.newLine();
                }
            } catch (FileNotFoundException exc) {
                System.out.printf("The reading file path %s is incorrect%n", file);
            } catch (IOException exc) {
                System.out.printf("Error %s in file %s",exc.getMessage(), file);
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
                System.out.printf("The directory for the recording file was not found." +
                        "The %s file will be placed in the current directory.%n", type);
                BufferedWriter writer = new BufferedWriter(new FileWriter(builder.toString(), options.isAppend()));
                writers.put(type, writer);
            } catch (IOException exc) {
                System.out.printf("Error creating %s a writing file in file %s%n",exc.getMessage(), basePath);
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
