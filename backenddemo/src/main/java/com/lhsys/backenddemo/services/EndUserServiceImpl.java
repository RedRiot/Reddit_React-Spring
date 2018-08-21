package com.lhsys.backenddemo.services;

import com.lhsys.backenddemo.models.dtos.EndUserDto;
import com.lhsys.backenddemo.models.entities.User;
import com.lhsys.backenddemo.models.entities.Role;
import com.lhsys.backenddemo.repositories.RoleRepository;
import com.lhsys.backenddemo.repositories.UserRepository;
import com.lhsys.backenddemo.services.interfaces.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userService")
public class EndUserServiceImpl implements UserDetailsService, EndUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User endUser = userRepository.findByName(username);
        if (endUser == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(endUser.getName(), endUser.getPassword(),
                getAuthority(endUser));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            // authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        // return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public User save(EndUserDto user) {
        User newEndUser = new User();
        newEndUser.setEmail(user.getEmail());
        newEndUser.setName(user.getUsername());
        newEndUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        //Role role = roleRepository.findByName(user.getRole());
        Role role = new Role("Admin","Admin");
        newEndUser.setRoles(Arrays.asList(role));

        return userRepository.save(newEndUser);
    }

}