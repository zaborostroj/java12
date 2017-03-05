package maketalents;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * PropReader reads the data.properties and sets default values
 */

class PropReader {

    private Properties properties;
    private String filePath;
    private static final String NOT_AVAILABLE = "N/A";
    private static final String DEF_PHOTO_PATH = "files/default_photo.jpg";

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
        filePath = path;
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) {
            this.readProperties();
        } else {
            System.out.println("Properties file not found");
        }
    }

    private void readProperties() {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(item->{
                if (!item.equals("")) {
                    String[] ar = item.split("=");
                    if (ar.length >= 2) {
                        properties.setProperty(ar[0], ar[1]);
                    }
                }
            });
        } catch (IOException ex) {
            System.out.println("Error reading data.properties");
            ex.printStackTrace();
        }
    }

    Properties getProperties() {
        return properties;
    }
}
