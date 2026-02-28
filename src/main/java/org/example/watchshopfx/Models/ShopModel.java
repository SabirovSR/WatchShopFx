package org.example.watchshopfx.Models;

import org.example.watchshopfx.IObserver;
import org.example.watchshopfx.InvalidWatchTimeException;
import org.example.watchshopfx.Watch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ShopModel implements Iterable<Watch> {
    private static final List<String> KNOWN_BRANDS = List.of(
            "Casio",
            "Citizen",
            "Seiko",
            "Tissot",
            "Orient",
            "Timex",
            "Swatch",
            "Fossil",
            "Hamilton",
            "Longines",
            "Omega",
            "Rolex",
            "Tag Heuer",
            "Breitling",
            "Garmin"
    );

    private final ArrayList<Watch> allWatches = new ArrayList<>();
    private final ArrayList<IObserver> allObserver = new ArrayList<>();

    public ShopModel() {
        addInitialWatch("Casio", 8900, "09:30");
        addInitialWatch("Seiko", 24500, "10:05");
        addInitialWatch("Tissot", 48900, "12:15");
        addInitialWatch("Citizen", 21900, "08:40");
        addInitialWatch("Orient", 19900, "17:25");
        addInitialWatch("Garmin", 32900, "06:55");
    }

    public List<Watch> getAllWatches() {
        return List.copyOf(allWatches);
    }

    public int countWatches() {
        return allWatches.size();
    }

    public int totalInventoryCost() {
        return allWatches.stream().mapToInt(Watch::getCost).sum();
    }

    public void addObserver(IObserver observer) {
        allObserver.add(observer);
        eventCall();
    }

    public void addWatch(Watch watch) {
        allWatches.add(watch);
        eventCall();
    }

    public void removeWatch(Watch watch) {
        allWatches.remove(watch);
        eventCall();
    }

    public void notifyChanged() {
        eventCall();
    }

    public void setTimeForAll(String time) throws InvalidWatchTimeException {
        for (Watch watch : allWatches) {
            watch.setTime(time);
        }
        eventCall();
    }

    public List<Watch> getMostExpensiveWatches() {
        if (allWatches.isEmpty()) {
            return List.of();
        }
        int maxCost = allWatches.stream().mapToInt(Watch::getCost).max().orElse(0);
        return allWatches.stream()
                .filter(watch -> watch.getCost() == maxCost)
                .collect(Collectors.toList());
    }

    public String mostFrequentBrand() {
        return getBrandStatisticsDesc().stream()
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse("-");
    }

    public List<Map.Entry<String, Integer>> getBrandStatisticsDesc() {
        Map<String, Integer> counts = new HashMap<>();
        for (Watch watch : allWatches) {
            counts.put(watch.getBrand(), counts.getOrDefault(watch.getBrand(), 0) + 1);
        }
        return counts.entrySet().stream()
                .sorted((a, b) -> {
                    int cmp = Integer.compare(b.getValue(), a.getValue());
                    if (cmp != 0) {
                        return cmp;
                    }
                    return a.getKey().compareToIgnoreCase(b.getKey());
                })
                .collect(Collectors.toList());
    }

    public List<String> getUniqueBrandsSorted() {
        return new ArrayList<>(new TreeSet<>(allWatches.stream()
                .map(Watch::getBrand)
                .collect(Collectors.toList())));
    }

    public List<String> getKnownBrands() {
        return KNOWN_BRANDS;
    }

    public List<String> getBrandSuggestionsSorted() {
        Set<String> result = new LinkedHashSet<>(KNOWN_BRANDS);
        result.addAll(getUniqueBrandsSorted());
        return result.stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    public List<Watch> getWatchesByCostDesc() {
        return allWatches.stream()
                .sorted(Comparator.comparingInt(Watch::getCost).reversed())
                .collect(Collectors.toList());
    }

    private void eventCall() {
        allObserver.forEach(action -> action.event(this));
    }

    private void addInitialWatch(String brand, int cost, String time) {
        try {
            allWatches.add(new Watch(brand, cost, time));
        } catch (InvalidWatchTimeException e) {
            throw new IllegalStateException("Invalid initial watch data", e);
        }
    }

    @Override
    public Iterator<Watch> iterator() {
        return allWatches.iterator();
    }
}

