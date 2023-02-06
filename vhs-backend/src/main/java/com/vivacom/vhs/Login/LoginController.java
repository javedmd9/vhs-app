package com.vivacom.vhs.Login;

import com.vivacom.vhs.model.Privilege;
import com.vivacom.vhs.model.PrivilegeRoles;
import com.vivacom.vhs.model.Roles;
import com.vivacom.vhs.model.Users;
import com.vivacom.vhs.repository.PrivilegeRepository;
import com.vivacom.vhs.repository.PrivilegeRoleRepository;
import com.vivacom.vhs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${token.expiry}")
    private Integer expiryTime;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    private PrivilegeRoleRepository privilegeRoleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserRepository usersRepository;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public AuthRequestDto generateToken(@RequestBody AuthRequestDto authRequestDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword())
            );
        } catch (Exception ex){
            throw new Exception("Invalid username or password");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, expiryTime);
        String token = jwtUtil.generateToken(authRequestDto.getUsername(), expiryTime);
        Roles role = service.findRolesByUsername(authRequestDto.getUsername());
        List<PrivilegeRoles> rolePrivilegeList = privilegeRoleRepository.findByRoleId(role.getId());
        List<Privilege> privilegeList = new ArrayList<>();
        for (PrivilegeRoles p: rolePrivilegeList){
            Privilege privilege = privilegeRepository.findById(p.getPrivilegeId()).get();
            privilegeList.add(privilege);
        }
        authRequestDto.setTokenExpiry(cal.getTime());
        authRequestDto.setToken(token);
        authRequestDto.setRoles(role);
        authRequestDto.setPrivilegeList(privilegeList);
        return authRequestDto;

    }

    @RequestMapping(value = "/find-by-username", method = RequestMethod.POST)
    public Users findByUsername(@RequestBody String username){
        return usersRepository.findByUserName(username);
    }
}
