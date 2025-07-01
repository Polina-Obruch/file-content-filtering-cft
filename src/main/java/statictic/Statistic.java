package statictic;

import properties.TypeString;

public interface Statistic {
    void addValue(TypeString type, String value);
    void getShortStats();
    void getFullStats();
}
