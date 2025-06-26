package reader;

import properties.TypeString;

import java.util.regex.Pattern;

public class Parser {
    private final Pattern patternFloat = Pattern.compile("-?\\d+(\\.\\d+)");
    private final Pattern patternInteger = Pattern.compile("-?\\d+\\d");

    public TypeString defineType(String string) {
        if (patternFloat.matcher(string).matches()) {
            return TypeString.FLOAT;
        } else if (patternInteger.matcher(string).matches()) {
            return TypeString.INTEGER;
        }
        return TypeString.STRING;
    }

    public void parseString(String str) {

    }

    public void parseInt(String str) {

    }

    public void parseFloat(String str) {

    }
}
