package properties;

import java.nio.file.Path;
import java.util.List;

public class Options {
    private Path path;
    private String name;
    private boolean isAppend;
    private StatisticType typeStat;
    private List<String> files;
    private boolean isStats;

    public Path getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public boolean isAppend() {
        return isAppend;
    }

    public StatisticType getTypeStat() {
        return typeStat;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppend(boolean append) {
        isAppend = append;
    }

    public void setTypeStat(StatisticType typeStat) {
        isStats = true;
        this.typeStat = typeStat;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public boolean isStats() {
        return isStats;
    }
}
