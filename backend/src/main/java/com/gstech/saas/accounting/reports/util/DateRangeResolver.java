package com.gstech.saas.accounting.reports.util;

import com.gstech.saas.accounting.reports.dto.DateRange;
import java.time.LocalDate;

public class DateRangeResolver {

    private DateRangeResolver() {}

    public static LocalDate resolveFrom(DateRange range, LocalDate customFrom) {
        LocalDate today = LocalDate.now();
        return switch (range) {
            case LAST_30_DAYS  -> today.minusDays(30);
            case LAST_QUARTER  -> today.minusMonths(3);
            case LAST_YEAR     -> today.minusYears(1);
            case THIS_YEAR     -> LocalDate.of(today.getYear(), 1, 1);
            case CUSTOM        -> customFrom;
        };
    }

    public static LocalDate resolveTo(DateRange range, LocalDate customTo) {
        return range == DateRange.CUSTOM ? customTo : LocalDate.now();
    }
}