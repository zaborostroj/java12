package maketalents.service;

import maketalents.dao.DataPropReader;
import maketalents.datamodel.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service for DataPropReader
 */
@Component
public class DataPropReaderService {
    @Autowired
    private DataPropReader dataPropReader;

    public DataPropReaderService() {}

    public UserData getUserData() {
        return dataPropReader.getUserData();
    }
}
