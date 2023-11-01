package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.UserDto;

import java.util.Collection;

public interface UserService {
    UserDto getById(Long id);
    UserDto add(UserDto user);
    UserDto update(UserDto user);
    void delete(Long id);
    Collection<UserDto> getAll();
}
