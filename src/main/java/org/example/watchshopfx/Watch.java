package org.example.watchshopfx;

import java.util.Objects;

public class Watch {
    private final String brand;
    private final int cost;
    private int hours;
    private int minutes;

    public Watch(String brand, int cost, String initialTime) throws InvalidWatchTimeException {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Марка часов не может быть пустой");
        }
        if (cost <= 0) {
            throw new IllegalArgumentException("Стоимость часов должна быть положительной");
        }
        this.brand = brand.trim();
        this.cost = cost;
        setTime(initialTime);
    }

    public String getBrand() {
        return brand;
    }

    public int getCost() {
        return cost;
    }

    public String getTimeString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    public void setTime(String time) throws InvalidWatchTimeException {
        int[] parsed = parseTime(time);
        hours = parsed[0];
        minutes = parsed[1];
    }

    private int[] parseTime(String time) throws InvalidWatchTimeException {
        if (time == null) {
            throw new InvalidWatchTimeException("Время не задано");
        }
        String normalized = time.trim();
        if (!normalized.matches("\\d{2}:\\d{2}")) {
            throw new InvalidWatchTimeException("Время должно быть в формате ЧЧ:ММ");
        }
        String[] parts = normalized.split(":");
        int parsedHours = Integer.parseInt(parts[0]);
        int parsedMinutes = Integer.parseInt(parts[1]);
        if (parsedHours < 0 || parsedHours > 23 || parsedMinutes < 0 || parsedMinutes > 59) {
            throw new InvalidWatchTimeException("Некорректное время: " + normalized);
        }
        return new int[]{parsedHours, parsedMinutes};
    }

    @Override
    public String toString() {
        return brand + " " + getTimeString() + " (" + cost + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Watch watch)) {
            return false;
        }
        return cost == watch.cost
                && hours == watch.hours
                && minutes == watch.minutes
                && Objects.equals(brand, watch.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, cost, hours, minutes);
    }
}

