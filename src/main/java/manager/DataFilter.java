package manager;

import properties.DataType;

import java.util.regex.Pattern;

public class DataFilter {
    private final Pattern patternFloat = Pattern.compile("^-?\\d+\\.\\d+([eE][+-]?\\d+)?$");
    private final Pattern patternNumberE = Pattern.compile("^-?\\d+[eE][+-]?\\d+?$");
    private final Pattern patternInteger = Pattern.compile("^-?\\d+$");

    public DataType defineType(String string) {
        if (patternFloat.matcher(string).matches() || patternNumberE.matcher(string).matches()) {
            return DataType.FLOAT;
        } else if (patternInteger.matcher(string).matches()) {
            return DataType.INTEGER;
        }
        return DataType.STRING;
    }
}
