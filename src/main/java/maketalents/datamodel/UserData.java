package maketalents.datamodel;

/**
 * Object holds user data read from .property file
 */
public class UserData {
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

    public UserData(
            String name,
            String birthDay,
            String phoneNumber,
            String email,
            String skype,
            String photo,
            String goal,
            String experience,
            String education,
            String addEducation,
            String otherInfo) {
        this.name = name;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.skype = skype;
        this.photo = photo;
        this.goal = goal;
        this.experience = experience;
        this.education = education;
        this.addEducation = addEducation;
        this.otherInfo = otherInfo;
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
}
