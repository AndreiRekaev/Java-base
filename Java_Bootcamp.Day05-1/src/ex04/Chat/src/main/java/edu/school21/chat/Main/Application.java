package edu.school21.chat.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Application {
    private static final Properties PROPERTIES = new Properties();
    private Application() {

    }
    static {
        try {
            loadProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void loadProperties() throws IOException {
        try (InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}