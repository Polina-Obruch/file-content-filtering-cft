package statictic;

import properties.TypeString;

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
    public void addValue(TypeString type, String value) {
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
        String statsNumber = " count: %s%n max: %s%n min: %s%n average: %s%n";
        String statsString = " count: %s%n max: %s%n min: %s%n";

        if (countString.equals(BigInteger.ZERO)) {
            System.out.printf(expForStatistic, TypeString.STRING);
        } else {
            System.out.printf(heading,TypeString.STRING);
            System.out.printf(statsString,
                    countString, maxLength, minLength);
        }

        if (countInt.equals(BigInteger.ZERO)) {
            System.out.printf(expForStatistic,TypeString.INTEGER);
        } else {
            System.out.printf(heading, TypeString.INTEGER);
            System.out.printf(statsNumber, countInt, maxInt, minInt, sumInt.divide(countInt));
        }

        if (countFloat.equals(BigDecimal.ZERO)) {
            System.out.printf(expForStatistic,TypeString.FLOAT);
        } else {
            System.out.printf(heading,TypeString.FLOAT);
            System.out.printf(statsNumber, countFloat, maxFloat, minFloat,
                    sumFloat.divide(countFloat, RoundingMode.CEILING));
        }
    }
}
