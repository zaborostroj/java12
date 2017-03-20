package maketalents.dao;

import maketalents.datamodel.UserData;
import maketalents.datamodel.UserPropertyKeys;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Implementation fo DataPropReaderIntf
 */
public class DataPropReader implements DataPropReaderIntf {
    /**
     * Properties holder
     */
    private Properties userPropFile;

    public DataPropReader(String propertyFilePath) {
        this.userPropFile = readPropertyFile(propertyFilePath);
    }

    /**
     * Read .properties file
     *
     * @param propertyFilePath - path to data.properties file (absolute from .jar root)
     * @return Properties
     */
    private Properties readPropertyFile(String propertyFilePath) {
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

        Properties properties = new Properties(defaultProperties);
        InputStream is = getClass().getResourceAsStream(propertyFilePath);

        try {
            properties.load(new InputStreamReader(is, "UTF-8"));

            return properties;
        } catch(IOException e) {
            System.out.println("Error reading .properties file");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserData getUserData() {
        UserData userData = null;
        if (this.userPropFile != null) {
            userData = new UserData(
                    userPropFile.getProperty(UserPropertyKeys.NAME),
                    userPropFile.getProperty(UserPropertyKeys.BIRTHDAY),
                    userPropFile.getProperty(UserPropertyKeys.PHONE_NUMBER),
                    userPropFile.getProperty(UserPropertyKeys.EMAIL),
                    userPropFile.getProperty(UserPropertyKeys.SKYPE),
                    userPropFile.getProperty(UserPropertyKeys.PHOTO),
                    userPropFile.getProperty(UserPropertyKeys.GOAL),
                    userPropFile.getProperty(UserPropertyKeys.EXPERIENCE),
                    userPropFile.getProperty(UserPropertyKeys.EDUCATION),
                    userPropFile.getProperty(UserPropertyKeys.ADD_EDUCATION),
                    userPropFile.getProperty(UserPropertyKeys.OTHER_INFO)
            );
        }
        return userData;
    }


    public Map<String, String> getUserDataFromHashMap() {
        Map<String, String> userData = new HashMap<>();

        userData.put(UserPropertyKeys.NAME, userPropFile.getProperty(UserPropertyKeys.NAME, "N/A"));
        userData.put(UserPropertyKeys.BIRTHDAY, userPropFile.getProperty(UserPropertyKeys.BIRTHDAY, "N/A"));
        userData.put(UserPropertyKeys.PHONE_NUMBER, userPropFile.getProperty(UserPropertyKeys.PHONE_NUMBER, "N/A"));
        userData.put(UserPropertyKeys.EMAIL, userPropFile.getProperty(UserPropertyKeys.EMAIL, "N/A"));
        userData.put(UserPropertyKeys.SKYPE, userPropFile.getProperty(UserPropertyKeys.SKYPE, "N/A"));
        userData.put(UserPropertyKeys.PHOTO, userPropFile.getProperty(UserPropertyKeys.PHOTO, "N/A"));
        userData.put(UserPropertyKeys.GOAL, userPropFile.getProperty(UserPropertyKeys.GOAL, "N/A"));
        userData.put(UserPropertyKeys.EXPERIENCE, userPropFile.getProperty(UserPropertyKeys.EXPERIENCE, "N/A"));
        userData.put(UserPropertyKeys.EDUCATION, userPropFile.getProperty(UserPropertyKeys.EDUCATION, "N/A"));
        userData.put(UserPropertyKeys.ADD_EDUCATION, userPropFile.getProperty(UserPropertyKeys.ADD_EDUCATION, "N/A"));
        userData.put(UserPropertyKeys.OTHER_INFO, userPropFile.getProperty(UserPropertyKeys.OTHER_INFO, "N/A"));

        userData.put(UserPropertyKeys.MULTYKEY_PROPERTIES, userPropFile.getProperty(UserPropertyKeys.MULTYKEY_PROPERTIES, ""));
        userData.put(UserPropertyKeys.PROPERTY_SEPARATOR, userPropFile.getProperty(UserPropertyKeys.PROPERTY_SEPARATOR, ""));

        return userData;
    }
}
