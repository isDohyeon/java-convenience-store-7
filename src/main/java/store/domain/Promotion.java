package store.domain;

import java.time.LocalDate;

public class Promotion {

    private final String name;
    private final int buyCount;
    private final int freeCount;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buyCount, int freeCount, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buyCount = buyCount;
        this.freeCount = freeCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isValidDate(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate))
                && (date.isEqual(endDate) || date.isBefore(endDate));
    }

    public String getName() {
        return name;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public int getFreeCount() {
        return freeCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
