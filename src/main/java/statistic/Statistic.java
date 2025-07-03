package statistic;

import properties.DataType;

public interface Statistic {
    void addValue(DataType type, String value);

    void getShortStats();

    void getFullStats();
}
