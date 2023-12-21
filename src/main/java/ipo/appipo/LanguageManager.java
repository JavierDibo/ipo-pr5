package ipo.appipo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LanguageManager {
    private Properties properties;

    public LanguageManager() {
        properties = new Properties();
    }

    public void cargarIdioma(String languageCode) {
        try {
            String path = "./idiomas/mensajes_" + languageCode + ".properties";
            FileInputStream input = new FileInputStream(path);

            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get_property(String key) {
        return properties.getProperty(key);
    }
}
