package com.spring.data.jpa.controller;

import com.spring.data.jpa.Entity.UserData;
import com.spring.data.jpa.Repository.UserDataRepository;
import com.spring.data.jpa.dao.DataPropReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class UserDataController {
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private DataPropReader dataPropReader;

    @RequestMapping(value="/")
    public String showHomePage() {
        return "homepage";
    }

    @RequestMapping(value="/find_in_db/")
    public String findDb(Model model, @RequestParam("id") Long id) {
        UserData userData = userDataRepository.findById(id);
        fillModel(model, userData);

        return "userDataTemplate";
    }

    @RequestMapping(value="/find_in_prop_file/")
    public String findInPropFile(Model model, @RequestParam("file_number") String fileNumber) {
        try {
            UserData userData = dataPropReader.getUserData("/res/data" + fileNumber + ".properties");
            fillModel(model, userData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "userDataTemplate";
    }

    private void fillModel(Model model, UserData userData) {
        model.addAttribute("name", userData.getName());
        model.addAttribute("birth_date", userData.getBirthDay());
        model.addAttribute("phone_number", userData.getPhoneNumber());
        model.addAttribute("email", userData.getEmail());
        model.addAttribute("skype", userData.getSkype());
        model.addAttribute("photo", userData.getPhoto());
        model.addAttribute("goal", userData.getGoal());
        model.addAttribute("experience", userData.getExperience());
        model.addAttribute("education", userData.getEducation());
        model.addAttribute("add_education", userData.getAddEducation());
        model.addAttribute("other_info", userData.getOtherInfo());
    }
}
