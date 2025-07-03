package statistic;

import properties.DataType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class StatisticImpl implements Statistic {
    private BigInteger countString = BigInteger.ZERO;
    private BigInteger minLength = null;
    private BigInteger maxLength = BigInteger.ZERO;

    private BigInteger countInt = BigInteger.ZERO;
    private BigInteger minInt = null;
    private BigInteger maxInt = null;
    private BigInteger sumInt = BigInteger.ZERO;

    private BigDecimal countFloat = BigDecimal.ZERO;
    private BigDecimal minFloat = null;
    private BigDecimal maxFloat = null;
    private BigDecimal sumFloat = BigDecimal.ZERO;


    @Override
    public void addValue(DataType type, String value) {
        try {
            switch (type) {
                case STRING -> {
                    countString = countString.add(BigInteger.ONE);
                    BigInteger crt = BigInteger.valueOf(value.length());

                    if (minLength == null || crt.compareTo(minLength) < 0) {
                        minLength = crt;
                    }

                    if (maxLength == null || crt.compareTo(maxLength) > 0) {
                        maxLength = crt;
                    }
                }
                case INTEGER -> {
                    countInt = countInt.add(BigInteger.ONE);
                    BigInteger crt = new BigInteger(value);

                    if (minInt == null || crt.compareTo(minInt) < 0) {
                        minInt = crt;
                    }

                    if (maxInt == null || crt.compareTo(maxInt) > 0) {
                        maxInt = crt;
                    }
                    sumInt = sumInt.add(crt);
                }
                case FLOAT -> {
                    countFloat = countFloat.add(BigDecimal.ONE);
                    BigDecimal crt = new BigDecimal(value);

                    if (minFloat == null || crt.compareTo(minFloat) < 0) {
                        minFloat = crt;
                    }

                    if (maxFloat == null || crt.compareTo(maxFloat) > 0) {
                        maxFloat = crt;
                    }
                    sumFloat = sumFloat.add(crt);
                }
            }
        } catch (ArithmeticException | NumberFormatException exp) {
            System.out.printf("The current value - %s is out of the supported range%n", value);
        } catch (OutOfMemoryError exp) {
            System.out.printf("There is not enough memory to work with such a large value %s%n", value);
        }
    }

    @Override
    public void getShortStats() {
        System.out.printf("COUNT STRING: %s%nCOUNT INTEGER: %s%nCOUNT FLOAT: %s%n", countString, countInt, countFloat);
    }

    @Override
    public void getFullStats() {
        String expForStatistic = "%nStatistics are not available for %s file. There are no suitable values in the source files.%n";
        String heading = "%nStatistic for %s file%n";
        String count = "%n%s file: count = 0";
        String statsNumber = " count: %s%n max: %s%n min: %s%n sum: %s%n average: %s%n";
        String statsString = " count: %s%n max: %s%n min: %s%n";

        if (countString.equals(BigInteger.ZERO)) {
            System.out.printf(count, DataType.STRING);
            System.out.printf(expForStatistic, DataType.STRING);
        } else {
            System.out.printf(heading, DataType.STRING);
            System.out.printf(statsString,
                    countString, maxLength, minLength);
        }

        if (countInt.equals(BigInteger.ZERO)) {
            System.out.printf(count, DataType.INTEGER);
            System.out.printf(expForStatistic, DataType.INTEGER);

        } else {
            System.out.printf(heading, DataType.INTEGER);
            System.out.printf(statsNumber, countInt, formatLargeNumber(maxInt),
                    formatLargeNumber(minInt), formatLargeNumber(sumInt), getAverage());
        }

        if (countFloat.equals(BigDecimal.ZERO)) {
            System.out.printf(count, DataType.FLOAT);
            System.out.printf(expForStatistic, DataType.FLOAT);
        } else {
            System.out.printf(heading, DataType.FLOAT);
            System.out.printf(statsNumber, countFloat, maxFloat, minFloat, sumFloat,
                    sumFloat.divide(countFloat, 10,RoundingMode.CEILING));
        }
    }

    private BigDecimal getAverage() {
        BigDecimal sum = new BigDecimal(sumInt);
        BigDecimal count = new BigDecimal(countInt);
        return sum.divide(count, 2,RoundingMode.CEILING);
    }

    private String formatLargeNumber(BigInteger num) {
        StringBuilder builder = new StringBuilder();
        builder.append(num);
        // Если число больше 50 цифр, используем сокращенную запись
        if (builder.length() > 50) {
            return builder.substring(0, 51) + "...E+" + (builder.length() - 50);
        }
        return builder.toString();
    }

    public BigInteger getCountString() {
        return countString;
    }

    public BigInteger getMinLength() {
        return minLength;
    }

    public BigInteger getMaxLength() {
        return maxLength;
    }

    public BigInteger getCountInt() {
        return countInt;
    }

    public BigInteger getMinInt() {
        return minInt;
    }

    public BigInteger getMaxInt() {
        return maxInt;
    }

    public BigInteger getSumInt() {
        return sumInt;
    }

    public BigDecimal getCountFloat() {
        return countFloat;
    }

    public BigDecimal getMinFloat() {
        return minFloat;
    }

    public BigDecimal getMaxFloat() {
        return maxFloat;
    }

    public BigDecimal getSumFloat() {
        return sumFloat;
    }
}
