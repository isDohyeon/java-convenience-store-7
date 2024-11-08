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
}
