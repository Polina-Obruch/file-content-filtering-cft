package properties;

import java.nio.file.Path;

public class Options {
    private Path path;
    private String name;
    private boolean isAppend;
    private TypeStatistic typeStat;
    private String[] files;
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

    public TypeStatistic getTypeStat() {
        return typeStat;
    }

    public String[] getFiles() {
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

    public void setTypeStat(TypeStatistic typeStat) {
        isStats = true;
        this.typeStat = typeStat;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public boolean isStats() {
        return isStats;
    }
}
