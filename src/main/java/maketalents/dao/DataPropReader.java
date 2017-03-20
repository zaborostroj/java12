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
            properties.list(System.out);

            return properties;
        } catch(IOException e) {
            System.out.println("Error reading .properties file");
            e.printStackTrace();
        }
            /*InputStream is = getClass().getResourceAsStream(propertyFilePath);
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
            return properties;
        } catch (IOException e) {
            System.out.println("Error reading .properties file");
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public UserData getUserData() {
        UserData userData = null;
        if (this.userPropFile != null) {
            Map<String, String> usData = new HashMap<>();

            String prop = userPropFile.getProperty(UserPropertyKeys.EXPERIENCE_M);
            if (prop != null) {
                for (String s : prop.split(";")) {
                    usData.put(UserPropertyKeys.EXPERIENCE_M, s);
                }
            } else {
                usData.put(
                        UserPropertyKeys.EXPERIENCE,
                        userPropFile.getProperty(UserPropertyKeys.EXPERIENCE, "N/A")
                );
            }
            System.out.println(usData.size());
            usData.forEach((k, v) -> System.out.println(k + " : " + v));

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
                    userPropFile.getProperty(UserPropertyKeys.OTHE_RINFO)
            );
        }
        return userData;
    }
}
