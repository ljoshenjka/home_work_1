package utils;

import constants.PropertyConfigs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Config reader
 */
public class ConfigReader {

    private static Properties configs = null;
    private static ConfigReader instance = null;
    private static final String DEFAULT_PROPERTIES = "config.properties";
    private static final String CONFIG_PATH = "src/main/resources/";

    private ConfigReader() {
        configs = new Properties();
        try {
            if (Files.isReadable(Paths.get(CONFIG_PATH + DEFAULT_PROPERTIES))) {
                configs.load(ConfigReader.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES));
                if (Files.isReadable(Paths.get(CONFIG_PATH + "config-" + getValue(PropertyConfigs.APP_ENV) + ".properties"))) {
                    configs.load(ConfigReader.class.getClassLoader().getResourceAsStream("config-" + getValue(PropertyConfigs.APP_ENV) + ".properties"));
                } else {
                    throw new IOException("Properties file config-" + getValue(PropertyConfigs.APP_ENV) + ".properties does not exist");
                }
            } else {
                throw new IOException("Properties file does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) instance = new ConfigReader();
        return instance;
    }

    public String getValue(String key) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        } else if (configs.containsKey(key)) {
            return configs.getProperty(key);
        }
        return null;
    }

    public String getValueOrDefault(String key, String defaultvalue) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        } else if (configs.containsKey(key)) {
            return configs.getProperty(key);
        }
        return defaultvalue;
    }

    public boolean hasValue(String key) {
        if (System.getProperty(key) != null) {
            return true;
        } else return configs.containsKey(key);
    }

}