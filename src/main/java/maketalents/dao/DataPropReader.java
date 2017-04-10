package maketalents.dao;

import maketalents.datamodel.UserData;

import java.io.IOException;

/**
 * Repo for getting user data
 */
public interface DataPropReader {
    String propertyFilePath = "/data2.properties";
    /**
     * @return UserData filled with fields that was read from .property file
     */
    UserData getUserData() throws IOException;
}
