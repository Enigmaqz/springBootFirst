package ru.netology.springBootDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springBootDemo.SystemProfile;

@RestController
@RequestMapping("/")
public class ProfileController {
    private SystemProfile profile;
  //  public void setProfile(SystemProfile profile) {
  //      this.profile = profile;
  //  }
    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }



    @GetMapping("profile")
    public String getProfile() {
        return profile.getProfile();
    }
}