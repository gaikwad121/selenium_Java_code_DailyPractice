package utils;

import java.util.Random;

public class TestDataGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String randomEmail() {
        return "user" + new Random().nextInt(10000) + "@test.com";
    }

    public static String randomName() {
        return "Name" + new Random().nextInt(10000);
    }

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
