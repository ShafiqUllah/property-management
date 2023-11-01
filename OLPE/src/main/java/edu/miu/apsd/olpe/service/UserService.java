package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.UserDto;
import edu.miu.apsd.olpe.entity.User;

import java.util.Collection;

public interface UserService {
    UserDto getById(Long id);
    public UserDto add(UserDto userDto,String password);
    UserDto update(Long userId, UserDto user);
    void delete(Long id);
    Collection<UserDto> getAll();
}
