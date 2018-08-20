package com.lhsys.backenddemo.controller;

import com.lhsys.backenddemo.models.dtos.EndUserDto;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.services.EndUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private EndUserService endUserService;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return endUserService.findAll();
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody EndUserDto user){
        return endUserService.save(user);
    }



}