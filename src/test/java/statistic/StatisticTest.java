package statistic;

import org.junit.Before;
import org.junit.Test;
import properties.DataType;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;


public class StatisticTest {
    private StatisticImpl stats = new StatisticImpl();

    @Before
    public void addTestData() {
        stats.addValue(DataType.INTEGER, "123");
        stats.addValue(DataType.INTEGER, "-456");
        stats.addValue(DataType.INTEGER, "0");

        stats.addValue(DataType.FLOAT, "-12e10");
        stats.addValue(DataType.FLOAT, "-1.23E-10");
        stats.addValue(DataType.FLOAT, "123.456");
        stats.addValue(DataType.FLOAT, "0.001");

        stats.addValue(DataType.STRING, "test");
        stats.addValue(DataType.STRING, "long string");
    }

    @Test
    public void testStatistics() {
        assertEquals(stats.getCountInt(), new BigInteger("3"));
        assertEquals(stats.getCountFloat(), new BigDecimal("4"));
        assertEquals(stats.getCountString(), new BigInteger("2"));

        assertEquals(stats.getMaxFloat(), new BigDecimal("123.456"));
        assertEquals(stats.getMinFloat(), new BigDecimal("-12e10"));

        assertEquals(stats.getMinLength(), new BigInteger("4"));
        assertEquals(stats.getMaxLength(), new BigInteger("11"));

        assertEquals(stats.getMaxInt(), new BigInteger("123"));
        assertEquals(stats.getMinInt(), new BigInteger("-456"));
    }
}
