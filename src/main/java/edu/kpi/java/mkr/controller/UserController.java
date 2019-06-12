package edu.kpi.java.mkr.controller;

import edu.kpi.java.mkr.controller.DTO.ChapterDetailsDTO;
import edu.kpi.java.mkr.controller.DTO.FullChapterDTO;
import edu.kpi.java.mkr.controller.DTO.UserDTO;
import edu.kpi.java.mkr.model.User;
import edu.kpi.java.mkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getChaptersByBook(@RequestParam("limit") Number limit, @RequestParam("offset") Number offset){
        return userService.findAllUsers(limit, offset).stream().map(UserDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getChapterWithText(@PathVariable("id") Number id){
        return UserDTO.from(userService.findUser(id));
    }
}
