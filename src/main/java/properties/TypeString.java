package properties;

public enum TypeString {
    STRING ("strings.txt"),
    INTEGER ("integers.txt"),
    FLOAT ("floats.txt");
    private final String fileName;

    TypeString(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
