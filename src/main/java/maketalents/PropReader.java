package maketalents;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * PropReader reads the data.properties and sets default values
 */

class PropReader {

    private Properties properties;
    private String propertyFilePath;
    private static final String NOT_AVAILABLE = "N/A";
    private static final String DEF_PHOTO_PATH = "default_photo.jpg";

    PropReader(String path) {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("name", NOT_AVAILABLE);
        defaultProperties.setProperty("birth_date", NOT_AVAILABLE);
        defaultProperties.setProperty("phone_number", NOT_AVAILABLE);
        defaultProperties.setProperty("email", NOT_AVAILABLE);
        defaultProperties.setProperty("skype", NOT_AVAILABLE);
        defaultProperties.setProperty("photo", DEF_PHOTO_PATH);
        defaultProperties.setProperty("goal", NOT_AVAILABLE);
        defaultProperties.setProperty("experience", NOT_AVAILABLE);
        defaultProperties.setProperty("education", NOT_AVAILABLE);
        defaultProperties.setProperty("add_education", NOT_AVAILABLE);
        defaultProperties.setProperty("other_info", NOT_AVAILABLE);

        properties = new Properties(defaultProperties);
        propertyFilePath = path;

        readProperties();
    }

    private void readProperties() {
        try {
            InputStream is = getClass().getResourceAsStream(propertyFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                // miss empty and commented with ";" lines
                if (!line.equals("") && !line.startsWith(";")) {
                    String[] ar = line.split("=");

                    // miss empty fields
                    if (ar.length >= 2) {
                        properties.setProperty(ar[0], ar[1]);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error reading data.properties");
            ex.printStackTrace();
        }
    }

    Properties getProperties() {
        return properties;
    }
}
