package maketalents.dao;

import maketalents.datamodel.UserData;
import maketalents.datamodel.UserPropertyKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Implementation for DataPropReaderIntf
 */
@Component
public class DataPropReader implements DataPropReaderIntf/*, Runnable */{
    /**
     * Properties holder
     */
    private Properties userPropFile;
    private UserData userData;


    @Autowired
    public DataPropReader(@Qualifier("userData") UserData userData) {
        this.userData = userData;
    }

//    @Override
//    public void run() {
//        this.userPropFile = readPropertyFile(propertyFilePath);
//        fillUserDataObject(this.userData);
//    }

    /**
     * Read .properties file
     *
     * @param propertyFilePath - path to data1.properties file (absolute from .jar root)
     * @return Properties
     */
    private Properties readPropertyFile(String propertyFilePath) {
        Properties properties = new Properties();
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
    public UserData fillUserDataObject(UserData userData) {
        this.userPropFile = readPropertyFile(propertyFilePath);

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
