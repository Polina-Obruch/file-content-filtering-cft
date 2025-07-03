package properties;

public enum DataType {
    STRING("strings.txt"),
    INTEGER("integers.txt"),
    FLOAT("floats.txt");
    private final String fileName;

    DataType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
