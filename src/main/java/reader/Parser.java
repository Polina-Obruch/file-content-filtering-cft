package reader;

import properties.TypeString;

import java.util.regex.Pattern;

public class Parser {
    private final Pattern patternFloat = Pattern.compile("^-?\\d+\\.\\d+([eE][+-]?\\d+)?$");
    private final Pattern patternNumberE = Pattern.compile("^-?\\d+[eE][+-]?\\d+?$");
    private final Pattern patternInteger = Pattern.compile("^-?\\d+$");

    public TypeString defineType(String string) {
        if (patternFloat.matcher(string).matches() || patternNumberE.matcher(string).matches()) {
            return TypeString.FLOAT;
        } else if (patternInteger.matcher(string).matches()) {
            return TypeString.INTEGER;
        }
        return TypeString.STRING;
    }
}
