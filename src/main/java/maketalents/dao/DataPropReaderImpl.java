package maketalents.dao;

import maketalents.datamodel.UserData;
import maketalents.datamodel.UserPropertyKeys;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * Implementation for DataPropReader
 */
@Component
public class DataPropReaderImpl implements DataPropReader {

    public DataPropReaderImpl() {}

    @Override
    public UserData getUserData() throws IOException {

        UserData userData = new UserData();

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
        if (singleProperty != null && userData.getPropertySeparator() != null) {
            ArrayList<String> multykeyProperties = new ArrayList<>();
            Collections.addAll(multykeyProperties, singleProperty.split(userData.getPropertySeparator()));
            userData.setMultykeyProperties(multykeyProperties);
        }

        return userData;
    }

    /**
     * Read .properties file
     *
     * @param propertyFilePath - path to data1.properties file (absolute from .jar root)
     * @return Properties
     */
    private Properties readPropertyFile(String propertyFilePath) throws IOException {
        Properties properties = new Properties();


        try (InputStream is = getClass().getResourceAsStream(propertyFilePath)) {
            properties.load(new InputStreamReader(is, "UTF-8"));

            return properties;
        } catch(IOException e) {
            System.out.println("Error reading .properties file");
            e.printStackTrace();
            throw new IOException(e);
        }
        //return null;
    }
}
