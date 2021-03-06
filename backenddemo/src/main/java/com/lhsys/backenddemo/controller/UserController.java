package com.lhsys.backenddemo.controller;

import com.lhsys.backenddemo.models.dtos.EndUserDto;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.services.interfaces.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private EndUserService endUserService;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> listUser(Principal principal){

        System.out.println(principal.getName());
        return endUserService.findAll();
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody EndUserDto user){
        return endUserService.save(user);
    }



}