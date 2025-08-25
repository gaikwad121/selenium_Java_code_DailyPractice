package utils;

import java.util.Base64;

public class CredentialsUtil {
    public static String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String decode(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }
}
