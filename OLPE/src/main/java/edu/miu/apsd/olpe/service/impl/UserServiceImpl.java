package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.CourseDto;
import edu.miu.apsd.olpe.dto.UserDto;
import edu.miu.apsd.olpe.entity.Course;
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
    public UserDto add(UserDto userDto,String password) {
        User res = userRepository.save(new User(null, userDto.getName(),userDto.getEmail(),password,userDto.getRoles()));
        return new UserDto(res.getName(),res.getEmail(),res.getRoles());
    }

    @Override
    public UserDto update(Long userId,UserDto newUser) {
        var user = userRepository.findById(userId).orElseThrow();

        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setRoles(newUser.getRoles());

        userRepository.save(user);

        return new UserDto(user.getName(),user.getEmail(),
                user.getRoles());

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
