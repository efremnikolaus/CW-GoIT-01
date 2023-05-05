package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CorruptionTest {

    @Test
    void calculateFirstTest() {
        int initialPercent = 5;
        List<Double> initialAccounts = new ArrayList<>();
        initialAccounts.add(50d);
        initialAccounts.add(50d);

        Double actual = new Corruption().calculate(initialPercent, initialAccounts);
        Double expected = 95d;

        Assertions.assertEquals(expected, actual);
    }
    @Test
    void inputValidationTest() {
        int initialPercent = -5;
        List<Double> initialAccounts = new ArrayList<>();
        initialAccounts.add(50d);
        initialAccounts.add(50d);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Corruption().calculate(initialPercent, initialAccounts));

        int initialPercent1 = 5;
        List<Double> initialAccounts1 = new ArrayList<>();
        initialAccounts1.add(50d);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Corruption().calculate(initialPercent1, initialAccounts1));
    }

    @Test
    void bigListCalculationTest() {
        int initialPercent = 5;
        List<Double> initialAccounts = new ArrayList<>();
        initialAccounts.add(1000d);
        initialAccounts.add(1100d);
        initialAccounts.add(1200d);
        initialAccounts.add(1300d);

        Double actual = new Corruption().calculate(initialPercent, initialAccounts);
        Double expected = 4151.50d;

        Assertions.assertEquals(expected, actual);
    }
    @Test
    void checkMinValueTest() {
        List<Double> initialAccounts = new ArrayList<>();
        initialAccounts.add(1000d);
        initialAccounts.add(1100d);
        initialAccounts.add(1200d);
        initialAccounts.add(1300d);

        Double actual = new Corruption().getMinValue(initialAccounts);
        Double expected = 1000d;

        Assertions.assertEquals(expected, actual);
    }
    @Test
    void calculateSmallMethodTest() {
        int initialPercent = 5;
        Double first = 1000d;
        Double second = 1100d;

        Double actual = new Corruption().calculate(initialPercent, first, second);
        Double expected = 1995.0d;

        Assertions.assertEquals(expected, actual);
    }
    @Test
    void inputValidationMaxValuesTest() {
        int initialPercent = 5;
        List<Double> initialAccounts = new ArrayList<>();
        initialAccounts.add(50d);
        initialAccounts.add(70001d);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Corruption().calculate(initialPercent, initialAccounts));
    }
    @Test
    void invalidAccountsTest() {
        int initialPercent = 5;
        List<Double> initialAccounts = new ArrayList<>();
        initialAccounts.add(50d);
        initialAccounts.add(Double.POSITIVE_INFINITY);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Corruption().calculate(initialPercent, initialAccounts));
    }
}