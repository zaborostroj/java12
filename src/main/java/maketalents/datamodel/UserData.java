package maketalents.datamodel;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Object holds user data read from .property file
 */
@Component
public class UserData {
    private String DEF_PHOTO_PATH = "https://pp.userapi.com/c629100/v629100402/4fe7f/4UOgawRYOhU.jpg";

    /**
     * Name
     */
    private String name;

    /**
     * Birthday
     */
    private String birthDay;

    /**
     * Phone number
     */
    private String phoneNumber;

    /**
     * E-mail
     */
    private String email;

    /**
     * Skype
     */
    private String skype;

    /**
     * Photo
     */
    private String photo;

    /**
     * Goal
     */
    private String goal;

    /**
     * Experience
     */
    private String experience;

    /**
     * Education
     */
    private String education;

    /**
     * Additional education, courses etc
     */
    private String addEducation;

    /**
     * Other info
     */
    private String otherInfo;

    /**
     * Values separator for multykey properties
     */
    private String propertySeparator;

    /**
     * Properties that should be converted to <ol/> in output.html
     */
    private ArrayList<String> multykeyProperties;

    public UserData() {
        String NOT_AVAILABLE = "N/A"; // Default value for empty fields
        name = NOT_AVAILABLE;
        birthDay = NOT_AVAILABLE;
        phoneNumber = NOT_AVAILABLE;
        email = NOT_AVAILABLE;
        skype = NOT_AVAILABLE;
        photo = DEF_PHOTO_PATH;
        goal = NOT_AVAILABLE;
        experience = NOT_AVAILABLE;
        education = NOT_AVAILABLE;
        addEducation = NOT_AVAILABLE;
        otherInfo = NOT_AVAILABLE;
        propertySeparator = null;
        multykeyProperties = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddEducation() {
        return addEducation;
    }

    public void setAddEducation(String addEducation) {
        this.addEducation = addEducation;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getPropertySeparator() {
        return propertySeparator;
    }

    public void setPropertySeparator(String propertySeparator) {
        this.propertySeparator = propertySeparator;
    }

    public void setMultykeyProperties(String multykeyProperties) {
        if (this.propertySeparator != null) {
            this.multykeyProperties = new ArrayList<>();
            Collections.addAll(this.multykeyProperties, multykeyProperties.split(this.propertySeparator));
        } else {
            this.multykeyProperties = null;
        }
    }

}
