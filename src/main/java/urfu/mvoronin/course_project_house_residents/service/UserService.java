package urfu.mvoronin.course_project_house_residents.service;

import urfu.mvoronin.course_project_house_residents.dto.UserDto;

public interface UserService {

    void saveUser(UserDto userDto);

    UserDto findUserByName(String name);
}
