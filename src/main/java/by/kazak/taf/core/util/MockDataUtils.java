package by.kazak.taf.core.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class MockDataUtils {

    public static int getRandomInt(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    public static String getRandomIntAsString(int min, int max) {
        return RandomStringUtils.randomNumeric(min, max);
    }

    public static String getRandomIntAsString(int length) {
        return getRandomIntAsString(0, length - 1);
    }

    public static String getRandomString(int min, int max) {
        return RandomStringUtils.randomAlphabetic(min, max);
    }

    public static String getRandomString(int length) {
        return getRandomString(0, length - 1);
    }

    public static String generateName() {
        return String.format("TAF_%s", getRandomString(10));
    }

    public static String generatePassword() {
        return String.format("%s%s", getRandomString(5), getRandomIntAsString(5));
    }

    public static String generateDescription() {
        return String.format("%s %s %s %s.", getRandomString(8, 10), getRandomString(5, 7), getRandomString(5, 7), getRandomString(5, 7));
    }
}
