package br.com.project.cineMood.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String CONFIG_FILE = "src/main/webapp/resources/config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Erro: "+ex.getMessage());
        }
    }

    public static String getApiKey() {
        return properties.getProperty("OMDB_API_KEY");
    }
}
