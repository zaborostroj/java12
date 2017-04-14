package com.spring.data.jpa.Entity;

public interface UserPropertyKeys {
    /**
     * Name
     */
    String NAME = "name";

    /**
     * Birthday
     */
    String BIRTHDAY = "birth_date";

    /**
     * Phone number
     */
    String PHONE_NUMBER = "phone_number";

    /**
     * E-mail
     */
    String EMAIL = "email";

    /**
     * Skype
     */
    String SKYPE = "skype";

    /**
     * Photo
     */
    String PHOTO = "photo";

    /**
     * Goal
     */
    String GOAL = "goal";

    /**
     * Experience
     */
    String EXPERIENCE = "experience";

    /**
     * Education
     */
    String EDUCATION = "education";

    /**
     * Additional education, courses etc
     */
    String ADD_EDUCATION = "add_education";

    /**
     * Other info
     */
    String OTHER_INFO = "other_info";

    /**
     * Properties that should be converted to <ol/> in output.html
     */
    String MULTYKEY_PROPERTIES = "multykey_properties";

    /**
     * Values separator for multykey properties
     */
    String PROPERTY_SEPARATOR = "property_separator";
}
