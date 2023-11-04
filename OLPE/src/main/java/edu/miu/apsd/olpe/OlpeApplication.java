package edu.miu.apsd.olpe;

import edu.miu.apsd.olpe.entity.*;
import edu.miu.apsd.olpe.repository.TeacherCourseRepository;
import edu.miu.apsd.olpe.repository.RoleRepository;
import edu.miu.apsd.olpe.repository.StudentCourseRepository;
import edu.miu.apsd.olpe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class OlpeApplication {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private TeacherCourseRepository teacherCourseRepository;
    private StudentCourseRepository studentCourseRepository;

    public static void main(String[] args) {
        System.out.println("Hello OLPE app, APSD Project");
        SpringApplication.run(OlpeApplication.class, args);
    }

    @PostConstruct
    public void initAdminUser() {
        var adminUser = userRepository.findByUsername("admin");
        if (adminUser.isEmpty()) {
            List<Role> listAdminRoles = new ArrayList<>();
            var adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
            if (adminRole.isEmpty()) {
                var newAdminRole = new Role(null, RoleTypes.ROLE_ADMIN.toString());
                listAdminRoles.add(newAdminRole);
            } else {
                listAdminRoles.add(adminRole.get());
            }
            User newAdminUser = new User(null, "Shafiq", "n/a", "Ullah",
                    "admin",
                    passwordEncoder.encode("test1234"), "admin@gmail.com",
                    true,true, true, true);
            newAdminUser.setRoles(listAdminRoles);
            userRepository.save(newAdminUser);

            //save teacher
            User newTeacherUser = new User(null, "O", "n/a", "Kallu",
                    "teacher",
                    passwordEncoder.encode("test1234"), "teacher@gmail.com",
                    true,true, true, true);
            newTeacherUser.setRoles(List.of(new Role(null, RoleTypes.ROLE_TEACHER.toString())));
            userRepository.save(newTeacherUser);

            //save student
            User newStudentUser = new User(null, "Sufi", "n/a", "Kader",
                    "student",
                    passwordEncoder.encode("test1234"), "student@gmail.com",
                    true,true, true, true);
            newStudentUser.setRoles(List.of(new Role(null, RoleTypes.ROLE_STUDENT.toString())));
            userRepository.save(newStudentUser);

            TeacherCourse newTeacherCourse = teacherCourseRepository.save(new TeacherCourse(
                    "cs489",
                    "APSD",
                    newTeacherUser,
                    false
                   ));

            TeacherCourse newTeacherCourse2 = teacherCourseRepository.save(new TeacherCourse(
                    "cs500",
                    "MPP",
                    newTeacherUser,
                    true
            ));

            studentCourseRepository.save(new StudentCourse(null,
                    newStudentUser,
                    newTeacherCourse,
                    false,
                    LocalDate.now(),
                    false));

            studentCourseRepository.save(new StudentCourse(null,
                    newStudentUser,
                    newTeacherCourse2,
                    false,
                    LocalDate.now(),
                    true));

        }
    }

}
