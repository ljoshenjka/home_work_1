package utils;

import exceptions.TestException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

/**
 * Properties writer
 */
public class PropertiesWriter {

    private final PropertiesConfiguration props;

    public PropertiesWriter(String filename) {
        this("src/test/resources/", filename);
    }

    public PropertiesWriter(String path, String filename) {
        try {
            props = new PropertiesConfiguration(new File(path + filename));
        } catch (ConfigurationException e) {
            throw new TestException("Error in reading properties file", e);
        }
    }

    public void setValue(String key, String value) {
        props.setProperty(key, value);
    }

    public void updateValue(String key, String value) {
        if (props.containsKey(key)) {
            props.setProperty(key, value);
        } else {
            throw new TestException("Key does not exist: " + key);
        }
    }

    public void saveData() {
        try {
            props.save();
        } catch (ConfigurationException e) {
            throw new TestException("Error in writing to properties file", e);
        }
    }

}
