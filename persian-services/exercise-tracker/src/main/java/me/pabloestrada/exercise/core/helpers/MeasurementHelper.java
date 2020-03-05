package me.pabloestrada.exercise.core.helpers;

public class MeasurementHelper {
    public static float convertMetersToMiles(final double meters) {
        return (float) meters * 0.00062137F;
    }

    public static float convertMilesToMeters(final float miles) {
        return miles * 1609.34F;
    }
}
