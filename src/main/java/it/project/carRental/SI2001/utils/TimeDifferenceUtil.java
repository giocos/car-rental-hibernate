package it.project.carRental.SI2001.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;

public final class TimeDifferenceUtil {

	private static final String SEMICOLON = "\\;";

	protected TimeDifferenceUtil() {}

    public static int difference(final String date) {
        final int[] values = convertToInteger(date.split(SEMICOLON));
        final DateTime start = new DateTime(values[0], values[1], values[2],
                values[3], values[4], values[5]);

        final DateTime end = new DateTime();
		final Days between = Days.daysBetween(start, end);
		return between.getDays();
    }

    private static int[] convertToInteger(final String[] splittedTime) {
        for (final String it : splittedTime) {
            System.out.print(it + " ");
        }

        final int[] integerTime = new int[splittedTime.length];
		for (int i = 0; i < splittedTime.length; i++) {
            integerTime[i] = Integer.parseInt(splittedTime[i]);
        }
        return integerTime;
    }
}
