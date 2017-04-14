package com.spring.data.jpa.Entity;

import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;

@Component
@Entity
public class UserData {
    @Id
    @GeneratedValue
    private Long id;
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
        String NOT_AVAILABLE = "N/A"; // Default value for empty fields
        String DEF_PHOTO_PATH = "https://pp.userapi.com/c629100/v629100402/4fe7f/4UOgawRYOhU.jpg"; // Default photo
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (this.getPropertySeparator() != null && this.getMultykeyProperties().contains(UserPropertyKeys.GOAL)) {
            return propertyToHtml(this.goal, this.propertySeparator);
        }
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getExperience() {
        if (this.getPropertySeparator() != null && this.getMultykeyProperties().contains(UserPropertyKeys.EXPERIENCE)) {
            return propertyToHtml(this.experience, this.propertySeparator);
        }
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        if (this.getPropertySeparator() != null && this.getMultykeyProperties().contains(UserPropertyKeys.EDUCATION)) {
            return propertyToHtml(this.education, this.propertySeparator);
        }
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddEducation() {
        if (this.getPropertySeparator() != null && this.getMultykeyProperties().contains(UserPropertyKeys.ADD_EDUCATION)) {
            return propertyToHtml(this.addEducation, this.propertySeparator);
        }
        return addEducation;
    }

    public void setAddEducation(String addEducation) {
        this.addEducation = addEducation;
    }

    public String getOtherInfo() {
        if (this.getPropertySeparator() != null && this.getMultykeyProperties().contains(UserPropertyKeys.OTHER_INFO)) {
            return propertyToHtml(this.otherInfo, this.propertySeparator);
        }
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

    public ArrayList getMultykeyProperties() {
        return multykeyProperties;
    }

    /**
     * Generates <ol/> tag from multivalue property
     *
     * @param prop - multivalue property
     * @return String - ordered list tag build from multivalue property
     */
    private String propertyToHtml(String prop, String propertySeparator) {
        String[] text = prop.split(propertySeparator);

        StringBuilder strBuilder = new StringBuilder();
        if (text.length == 1) {
            strBuilder.append(text[0]);
        } else if (text.length > 1) {
            strBuilder.append("<ol>");
            for (String str : text) {
                strBuilder.append("<li>").append(str).append("</li>");
            }
            strBuilder.append("</ol>");
        }

        return strBuilder.toString();
    }
}
