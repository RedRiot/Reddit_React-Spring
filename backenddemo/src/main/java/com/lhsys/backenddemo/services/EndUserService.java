package com.lhsys.backenddemo.services;

import com.lhsys.backenddemo.models.dtos.EndUserDto;
import com.lhsys.backenddemo.models.entities.User;


import java.util.List;

public interface EndUserService {

    User save(EndUserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    User findById(Long id);
}