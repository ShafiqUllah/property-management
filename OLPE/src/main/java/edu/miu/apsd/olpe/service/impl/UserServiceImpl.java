package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.UserDto;
import edu.miu.apsd.olpe.entity.User;
import edu.miu.apsd.olpe.repository.UserRepository;
import edu.miu.apsd.olpe.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow();
        return new UserDto(user.getName(), user.getEmail(), user.getRoles());
    }

    @Override
    public UserDto add(UserDto user) {
        return null;
    }

    @Override
    public UserDto update(UserDto user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Collection<UserDto> getAll() {
       return this.userRepository.findAll().stream()
               .map(u->new UserDto(u.getName(),u.getEmail(),u.getRoles()))
               .toList();
    }
}
