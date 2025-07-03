package manager;

import org.junit.Test;
import properties.DataType;

import static org.junit.Assert.assertEquals;


public class DataFilterTest {

    @Test
    public void testDefineType() {
        DataFilter filter = new DataFilter();

        assertEquals(DataType.INTEGER, filter.defineType("123"));
        assertEquals(DataType.INTEGER, filter.defineType("-123"));

        assertEquals(DataType.FLOAT, filter.defineType("123.456"));
        assertEquals(DataType.FLOAT, filter.defineType("-123.456"));
        assertEquals(DataType.FLOAT, filter.defineType("1.23e10"));
        assertEquals(DataType.FLOAT, filter.defineType("12e10"));
        assertEquals(DataType.FLOAT, filter.defineType("-12e10"));
        assertEquals(DataType.FLOAT, filter.defineType("-1.23E-10"));

        assertEquals(DataType.STRING, filter.defineType("abc"));
        assertEquals(DataType.STRING, filter.defineType("abc_df/hj"));
        assertEquals(DataType.STRING, filter.defineType("123abc"));
        assertEquals(DataType.STRING, filter.defineType("123.abc"));
        assertEquals(DataType.STRING, filter.defineType(""));
    }
}
