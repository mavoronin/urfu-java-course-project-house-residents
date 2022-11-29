package urfu.mvoronin.course_project_house_residents.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import urfu.mvoronin.course_project_house_residents.dto.UserDto;
import urfu.mvoronin.course_project_house_residents.entity.User;
import urfu.mvoronin.course_project_house_residents.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        var user = new User();
        user.setName(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public UserDto findUserByName(String name) {
        var user = userRepository.findByName(name);
        return user == null ? null : mapToUserDto(user);
    }

    private UserDto mapToUserDto(User user) {
        var userDto = new UserDto();
        userDto.setUsername(user.getName());
        return userDto;
    }
}
