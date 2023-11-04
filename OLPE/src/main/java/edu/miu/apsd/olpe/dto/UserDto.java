package edu.miu.apsd.olpe.dto;

import edu.miu.apsd.olpe.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String username;
    String password;
    List<Role> roles;

}
