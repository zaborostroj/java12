package maketalents.dao;

import maketalents.datamodel.UserData;
import maketalents.datamodel.UserPropertyKeys;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Implementation for DataPropReader
 */
public class DataPropReaderImpl implements DataPropReader {
    private String propertyFilePath;

    public DataPropReaderImpl(String propertyFilePath) {
        if (!propertyFilePath.equals("")) {
            this.propertyFilePath = propertyFilePath;
        }
    }

    /**
     * Read .properties file
     *
     * @param propertyFilePath - path to data1.properties file (absolute from .jar root)
     * @return Properties
     */
    private Properties readPropertyFile(String propertyFilePath) {
        Properties properties = new Properties();

        InputStream is = getClass().getResourceAsStream(propertyFilePath /*"/res/data1.properties"*/);

        try {
            properties.load(new InputStreamReader(is, "UTF-8"));

            return properties;
        } catch(IOException e) {
            System.out.println("Error reading .properties file");
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param userData - UserData object holding properties read from .property file
     * @return UserData
     */
    @Override
    public UserData fillUserDataObject(UserData userData) {
        /* Properties holder */
        Properties userPropFile = readPropertyFile(propertyFilePath);

        assert userPropFile != null;
        String singleProperty = userPropFile.getProperty(UserPropertyKeys.NAME);
        if (singleProperty != null) {
            userData.setName(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.BIRTHDAY);
        if (singleProperty != null) {
            userData.setBirthDay(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.PHONE_NUMBER);
        if (singleProperty != null) {
            userData.setPhoneNumber(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.EMAIL);
        if (singleProperty != null) {
            userData.setEmail(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.SKYPE);
        if (singleProperty != null) {
            userData.setSkype(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.PHOTO);
        if (singleProperty != null) {
            userData.setPhoto(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.GOAL);
        if (singleProperty != null) {
            userData.setGoal(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.EXPERIENCE);
        if (singleProperty != null) {
            userData.setExperience(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.EDUCATION);
        if (singleProperty != null) {
            userData.setEducation(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.ADD_EDUCATION);
        if (singleProperty != null) {
            userData.setAddEducation(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.OTHER_INFO);
        if (singleProperty != null) {
            userData.setOtherInfo(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.PROPERTY_SEPARATOR);
        if (singleProperty != null) {
            userData.setPropertySeparator(singleProperty);
        }

        singleProperty = userPropFile.getProperty(UserPropertyKeys.MULTYKEY_PROPERTIES);
        if (singleProperty != null) {
            userData.setMultykeyProperties(singleProperty);
        }

        return userData;
    }
}
