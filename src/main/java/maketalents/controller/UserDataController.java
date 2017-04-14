package maketalents.controller;

import maketalents.datamodel.UserData;
import maketalents.Repository.UserDataRepository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserDataController {
//    @Autowired
//    private UserData userDataReadFromFile;

    @Autowired
    private UserDataRepository userDataRepository;

    @RequestMapping("/")
    public String homePage() {
        return "homepage";
    }

//    @RequestMapping("/read_props")
//    public String getUserData(Model model, @RequestParam(value="prop_file_path") String propFilePath) {
//        DataPropReaderImpl dataPropReader = new DataPropReaderImpl("/res/" + propFilePath);
//        userDataReadFromFile = dataPropReader.fillUserDataObject(userDataReadFromFile); // может стоит сделать метод void???
//
//        model.addAttribute("name", userDataReadFromFile.getName());
//        model.addAttribute("birth_date", userDataReadFromFile.getBirthDay());
//        model.addAttribute("phone_number", userDataReadFromFile.getPhoneNumber());
//        model.addAttribute("email", userDataReadFromFile.getEmail());
//        model.addAttribute("skype", userDataReadFromFile.getSkype());
//        model.addAttribute("photo", userDataReadFromFile.getPhoto());
//
//        // TODO: допилить нормальное отображение множественных свойств
//        model.addAttribute("goal", userDataReadFromFile.getGoal());
//        model.addAttribute("experience", userDataReadFromFile.getExperience());
//        model.addAttribute("education", userDataReadFromFile.getEducation());
//        model.addAttribute("add_education", userDataReadFromFile.getAddEducation());
//        model.addAttribute("other_info", userDataReadFromFile.getOtherInfo());
//
//        return "userDataTemplate";
//    }

    @RequestMapping(value="/find_db/")
    public String findDb(@RequestParam("id") Long id) {
        List<UserData> userData = userDataRepository.findById(id);
        StringBuilder stringBuilder = new StringBuilder();
        userData.forEach(user -> {
            stringBuilder.append(user.getId() + " ");
            stringBuilder.append(user.getName() + " ");
            stringBuilder.append(user.getGoal() + "<br/>");
        });
        return "homepage";
    }
}
