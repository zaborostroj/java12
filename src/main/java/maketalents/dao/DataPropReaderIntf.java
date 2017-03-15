package maketalents.dao;

import maketalents.datamodel.UserData;

/**
 * Repo for getting user data
 */
public interface DataPropReaderIntf {
    String NOT_AVAILABLE = "N/A";
    String DEF_PHOTO_PATH = "https://pp.userapi.com/c629100/v629100402/4fe7f/4UOgawRYOhU.jpg";

    /**
     * @return UserData
     */
    UserData getUserData();
}
