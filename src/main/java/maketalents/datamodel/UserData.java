package maketalents.datamodel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Object holds user data read from .property file
 */
public class UserData {
    private String name;
    private String birthDay;
    private String phoneNumber;
    private String email;
    private String skype;
    private String photo;
    private String goal;
    private String experience;
    private String education;
    private String addEducation;
    private String otherInfo;
    private String propertySeparator;
    private ArrayList<String> multykeyProperties;

    public UserData() {
        name = null;
        birthDay = null;
        phoneNumber = null;
        email = null;
        skype = null;
        photo = "https://pp.userapi.com/c629100/v629100402/4fe7f/4UOgawRYOhU.jpg";
        goal = null;
        experience = null;
        education = null;
        addEducation = null;
        otherInfo = null;
        propertySeparator = null;
        multykeyProperties = null;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized String getBirthDay() {
        return birthDay;
    }

    public synchronized void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public synchronized String getPhoneNumber() {
        return phoneNumber;
    }

    public synchronized void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public synchronized String getEmail() {
        return email;
    }

    public synchronized void setEmail(String email) {
        this.email = email;
    }

    public synchronized String getSkype() {
        return skype;
    }

    public synchronized void setSkype(String skype) {
        this.skype = skype;
    }

    public synchronized String getPhoto() {
        return photo;
    }

    public synchronized void setPhoto(String photo) {
        this.photo = photo;
    }

    public synchronized String getGoal() {
        return goal;
    }

    public synchronized void setGoal(String goal) {
        this.goal = goal;
    }

    public synchronized String getExperience() {
        return experience;
    }

    public synchronized void setExperience(String experience) {
        this.experience = experience;
    }

    public synchronized String getEducation() {
        return education;
    }

    public synchronized void setEducation(String education) {
        this.education = education;
    }

    public synchronized String getAddEducation() {
        return addEducation;
    }

    public synchronized void setAddEducation(String addEducation) {
        this.addEducation = addEducation;
    }

    public synchronized String getOtherInfo() {
        return otherInfo;
    }

    public synchronized void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public synchronized String getPropertySeparator() {
        return propertySeparator;
    }

    public synchronized void setPropertySeparator(String propertySeparator) {
        this.propertySeparator = propertySeparator;
    }

    public synchronized ArrayList<String> getMultykeyProperties() {
        return this.multykeyProperties;
    }

    public synchronized void setMultykeyProperties(String multykeyProperties) {
        if (this.propertySeparator != null) {
            this.multykeyProperties = new ArrayList<>();
            Collections.addAll(this.multykeyProperties, multykeyProperties.split(this.propertySeparator));
        } else {
            this.multykeyProperties = null;
        }
    }

}
