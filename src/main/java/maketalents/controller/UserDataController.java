package maketalents.controller;

import maketalents.dao.DataPropReader;
import maketalents.datamodel.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserDataController {
    @Autowired
    private UserData userDataReadFromFile;

    @RequestMapping("/")
    public String homePage() {
        return "homepage";
    }

    @RequestMapping("/read_props")
    public String getUserData(Model model, @RequestParam(value="prop_file_path") String propFilePath) {
        DataPropReader dataPropReader = new DataPropReader("/res/" + propFilePath);
        userDataReadFromFile = dataPropReader.fillUserDataObject(userDataReadFromFile); // может стоит сделать метод void???

        model.addAttribute("name", userDataReadFromFile.getName());
        model.addAttribute("birth_date", userDataReadFromFile.getBirthDay());
        model.addAttribute("phone_number", userDataReadFromFile.getPhoneNumber());
        model.addAttribute("email", userDataReadFromFile.getEmail());
        model.addAttribute("skype", userDataReadFromFile.getSkype());
        model.addAttribute("photo", userDataReadFromFile.getPhoto());

        // TODO: допилить нормальное отображение множественных свойств
        if (userDataReadFromFile.getPropertySeparator() != null && userDataReadFromFile.getMultykeyProperties().contains("goal")) {
            model.addAttribute("goal", propertyToHtml(userDataReadFromFile.getGoal(), userDataReadFromFile.getPropertySeparator()));
        } else {
            model.addAttribute("goal", userDataReadFromFile.getGoal());
        }

        if (userDataReadFromFile.getPropertySeparator() != null && userDataReadFromFile.getMultykeyProperties().contains("experience")) {
            model.addAttribute("experience", propertyToHtml(userDataReadFromFile.getGoal(), userDataReadFromFile.getPropertySeparator()));
        } else {
            model.addAttribute("experience", userDataReadFromFile.getExperience());
        }

        if (userDataReadFromFile.getPropertySeparator() != null && userDataReadFromFile.getMultykeyProperties().contains("education")) {
            model.addAttribute("education", propertyToHtml(userDataReadFromFile.getGoal(), userDataReadFromFile.getPropertySeparator()));
        } else {
            model.addAttribute("education", userDataReadFromFile.getEducation());
        }

        if (userDataReadFromFile.getPropertySeparator() != null && userDataReadFromFile.getMultykeyProperties().contains("add_education")) {
            model.addAttribute("add_education", propertyToHtml(userDataReadFromFile.getGoal(), userDataReadFromFile.getPropertySeparator()));
        } else {
            model.addAttribute("add_education", userDataReadFromFile.getAddEducation());
        }

        if (userDataReadFromFile.getPropertySeparator() != null && userDataReadFromFile.getMultykeyProperties().contains("other_info")) {
            model.addAttribute("other_info", propertyToHtml(userDataReadFromFile.getGoal(), userDataReadFromFile.getPropertySeparator()));
        } else {
            model.addAttribute("other_info", userDataReadFromFile.getOtherInfo());
        }

        return "userDataTemplate";
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
