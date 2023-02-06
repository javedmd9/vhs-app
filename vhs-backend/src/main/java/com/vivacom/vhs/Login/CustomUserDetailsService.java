package com.vivacom.vhs.Login;

import com.vivacom.vhs.model.Roles;
import com.vivacom.vhs.model.Users;
import com.vivacom.vhs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserName(username);
        return new User(users.getUserName(), users.getPassword(), new ArrayList<>());
    }

    public Roles findRolesByUsername(String username) {
        Users byUserName = usersRepository.findByUserName(username);
        Roles roles = byUserName.getRoles();
        return roles;
    }

}
