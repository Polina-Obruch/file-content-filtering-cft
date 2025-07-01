import properties.Options;
import properties.TypeStatistic;
import reader.FileManager;

import java.nio.file.Path;
import java.util.Arrays;

/**
 -o <путь> - путь для сохранения результатов (по умолчанию - текущая директория)
-p <префикс> - префикс имен выходных файлов (по умолчанию - "")
-a - режим добавления в существующие файлы (по умолчанию - перезапись)
-s - вывод краткой статистики
-f - вывод полной статистики (если не указана ни одна из опций статистики, статистика не выводится)
 */

public class FileContentFilteringApp {

    public static void main(String[] args) {
        Options options = new Options();
        int size = args.length;

        if (size == 0) {
            System.out.println("For the utility to work at startup, you must specify the desired options and source files for filtering.");
            return;
        }

        for (int i = 0; i < size; ++i) {
            String crt = args[i];

            switch (crt) {
                case "-o" -> options.setPath(Path.of(args[++i]));
                case "-p" -> options.setName(args[++i]);
                case "-s" -> options.setTypeStat(TypeStatistic.SHORT);
                case "-f" -> options.setTypeStat(TypeStatistic.FULL);
                case "-a" -> options.setAppend(true);
                default -> {
                    options.setFiles(Arrays.copyOfRange(args, i, size));
                    i = size;
                }
            }
        }

        FileManager fileManager = new FileManager(options);
        fileManager.workWithFiles();
    }
}
