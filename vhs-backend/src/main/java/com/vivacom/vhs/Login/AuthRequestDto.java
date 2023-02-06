package com.vivacom.vhs.Login;

import com.vivacom.vhs.model.Privilege;
import com.vivacom.vhs.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;
    private String token;
    private Date tokenExpiry;
    private String errorMessage;
    private Roles roles;
    private List<Privilege> privilegeList;
}
