package edu.miu.apsd.olpe.service.impl;

import edu.miu.apsd.olpe.dto.UserDto;
import edu.miu.apsd.olpe.entity.User;
import edu.miu.apsd.olpe.exception.UserNotFoundException;
import edu.miu.apsd.olpe.repository.UserRepository;
import edu.miu.apsd.olpe.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getById(Long id) throws UserNotFoundException {
        System.out.println("XXXXXXXXXXXX "+id);
        User user = this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Data not found"));
        System.out.println(user);
        return new UserDto(user.getUsername(), user.getPassword(), user.getRoles());
    }

    @Override
    public UserDto add(UserDto userDto) {

        User res = new User(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()));
        res.setRoles(userDto.getRoles());
        User res2 = userRepository.save(res);
        return new UserDto(res2.getUsername(), res2.getPassword(),res2.getRoles());
    }

    @Override
    public UserDto update(Long userId,UserDto newUser) {
        var user = userRepository.findById(userId).orElseThrow();

        user.setFirstName(newUser.getUsername());
        user.setEmail(newUser.getPassword());
        user.setRoles(newUser.getRoles());

        userRepository.save(user);

        return new UserDto(user.getFullName(),user.getEmail(),
                user.getRoles());

    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Collection<UserDto> getAll() {
       return this.userRepository.findAll().stream()
               .map(u->new UserDto(u.getUsername(),u.getEmail(),u.getRoles()))
               .toList();
    }
}
