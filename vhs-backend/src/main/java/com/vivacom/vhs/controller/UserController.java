package com.vivacom.vhs.controller;

import com.vivacom.vhs.Login.AuthRequestDto;
import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.model.Users;
import com.vivacom.vhs.repository.UserRepository;
import com.vivacom.vhs.utils.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public StatusDto createNewUser(@RequestBody Users user){
        Users existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser != null){
            return StatusDto.builder().result(Status.FAILED).message("Cannot save. User name already exist").build();
        }
        userRepository.save(user);
        return StatusDto.builder().result(Status.SUCCESS).message("Successfully saved new user").build();
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

}
