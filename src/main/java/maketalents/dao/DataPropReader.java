package maketalents.dao;

import maketalents.datamodel.UserData;

/**
 * Repo for getting user data
 */
public interface DataPropReader {
    //String propertyFilePath = "/data1.properties";
    /**
     * @return UserData filled with fields that was read from .property file
     */
    UserData fillUserDataObject(UserData userData);
}
