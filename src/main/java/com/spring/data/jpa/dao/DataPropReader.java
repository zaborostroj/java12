package com.spring.data.jpa.dao;

import com.spring.data.jpa.Entity.UserData;
import java.io.IOException;

/**
 * Repo for getting user data
 */
public interface DataPropReader {
    String propertyFilePath = "/res/data1.properties";
    /**
     * @return UserData filled with fields that was read from .property file
     */
    UserData getUserData(String propertyFilePath) throws IOException;
}
