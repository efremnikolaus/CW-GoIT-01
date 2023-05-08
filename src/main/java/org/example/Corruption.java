package org.example;

import java.util.List;

public class Corruption {

    public Double calculate(int percent, List<Double> accounts) {
        validateInputs(percent, accounts);
        while (accounts.size() != 1) {
            workMethod(percent, accounts);
        }
        return accounts.get(0);
    }

    private void workMethod(int percent, List<Double> accounts) {
        Double getFirstValue = getMinValue(accounts);
        accounts.remove(getFirstValue);
        Double getSecondValue = getMinValue(accounts);
        accounts.remove(getSecondValue);

        Double result = calculate(percent, getFirstValue, getSecondValue);
        accounts.add(result);
    }

    public Double calculate(int percent, Double getFirstValue, Double getSecondValue) {
        Double percentSum = (double) ((getFirstValue + getSecondValue)/100)*percent;
        return getFirstValue + getSecondValue - percentSum;
    }

    public Double getMinValue(List<Double> accounts) {
        return accounts.stream().mapToDouble(v -> v).min().getAsDouble();
    }

    private void validateInputs(int percent, List<Double> accounts) {
        if (percent <= 0 || percent > 100){
            throw new IllegalArgumentException("Ooopppsss..Bad percent: " + percent);
        }
        if (accounts.isEmpty() || accounts.size() < 2) {
            throw new IllegalArgumentException("Must be several accounts!");
        }
        if (accounts.stream().anyMatch(x -> x > 70000)) {
            throw new IllegalArgumentException("Accounts should not be greater than 70000!");
        }
        if (accounts.stream().anyMatch(x -> x.isInfinite())) {
            throw new IllegalArgumentException("Oooppss.. mistake :(");
        }
    }
}