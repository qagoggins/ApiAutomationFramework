package io.framework.Utils;

public class RandomUtils {
    private RandomUtils() {}

    public static int getId() {
        return FakerUtils.getNumber(30, 100);
    }
    public static String getFirstName() {
        return FakerUtils.getFirstName().toLowerCase();
    }

    public static String getLastName() {
        return FakerUtils.getLastName().toLowerCase();
    }
}
