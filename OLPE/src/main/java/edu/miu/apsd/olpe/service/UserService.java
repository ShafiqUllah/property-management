package edu.miu.apsd.olpe.service;

import edu.miu.apsd.olpe.dto.UserDto;
import edu.miu.apsd.olpe.entity.User;
import edu.miu.apsd.olpe.exception.UserNotFoundException;

import java.util.Collection;

public interface UserService {
    UserDto getById(Long id) throws UserNotFoundException;
    public UserDto add(UserDto userDto);
    UserDto update(Long userId, UserDto user);
    void delete(Long id);
    Collection<UserDto> getAll();
}
